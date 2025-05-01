<template>
  <div class="review-transfer-container">
    <el-card>
      <template #header>
        <h2>转会申请审核</h2>
      </template>

      <el-table :data="pendingTransfers" v-loading="loading">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column label="选手">
          <template #default="{ row }">
            {{ row.player?.name || 'N/A' }}
          </template>
        </el-table-column>
        <el-table-column label="原俱乐部">
          <template #default="{ row }">
            {{ row.fromClub?.name || 'N/A' }}
          </template>
        </el-table-column>
        <el-table-column label="目标俱乐部">
          <template #default="{ row }">
            {{ row.toClub?.name || 'N/A' }}
          </template>
        </el-table-column>
        <el-table-column label="转会费">
          <template #default="{ row }">
            {{ formatCurrency(row.transferFee) }}
          </template>
        </el-table-column>
        <el-table-column label="申请时间">
          <template #default="{ row }">
            {{ formatDate(row.createdAt) }}
          </template>
        </el-table-column>
        <el-table-column label="状态">
          <template #default="{ row }">
            <el-tag :type="statusTagType(row.status)">
              {{ statusText[row.status] }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="220">
          <template #default="{ row }">
            <el-button
                type="success"
                size="small"
                @click="handleReview(row.id, 'approved')"
                :disabled="row.status !== 'pending'"
            >
              批准
            </el-button>
            <el-button
                type="danger"
                size="small"
                @click="handleReview(row.id, 'rejected')"
                :disabled="row.status !== 'pending'"
            >
              拒绝
            </el-button>
            <el-button
                type="info"
                size="small"
                @click="showDetails(row)"
            >
              详情
            </el-button>
          </template>
        </el-table-column>
      </el-table>

      <!-- 审核详情对话框 -->
      <el-dialog v-model="detailVisible" title="转会详情" width="50%">
        <el-descriptions v-if="currentTransfer" :column="2" border>
          <el-descriptions-item label="选手">{{ currentTransfer.player?.name }}</el-descriptions-item>
          <el-descriptions-item label="原俱乐部">{{ currentTransfer.fromClub?.name }}</el-descriptions-item>
          <el-descriptions-item label="目标俱乐部">{{ currentTransfer.toClub?.name }}</el-descriptions-item>
          <el-descriptions-item label="转会费">{{ formatCurrency(currentTransfer.transferFee) }}</el-descriptions-item>
          <el-descriptions-item label="申请时间">{{ formatDate(currentTransfer.createdAt) }}</el-descriptions-item>
          <el-descriptions-item label="当前状态">
            <el-tag :type="statusTagType(currentTransfer.status)">
              {{ statusText[currentTransfer.status] }}
            </el-tag>
          </el-descriptions-item>
          <el-descriptions-item label="备注" :span="2">{{ currentTransfer.notes || '无' }}</el-descriptions-item>
        </el-descriptions>
      </el-dialog>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useTransfersStore } from '@/stores/transfers'
import { ElMessage, ElMessageBox } from 'element-plus'
import { formatDate, formatCurrency } from '@/utils/format'

const transfersStore = useTransfersStore()
const loading = ref(false)
const detailVisible = ref(false)
const currentTransfer = ref(null)

// 状态显示配置
const statusText = {
  pending: '待审核',
  approved: '已批准',
  rejected: '已拒绝',
  completed: '已完成'
}

// 获取待审核转会列表
const pendingTransfers = computed(() => {
  return transfersStore.transfers.filter(t => t.status === 'pending')
})

// 状态标签类型
const statusTagType = (status) => {
  const types = {
    pending: 'warning',
    approved: 'success',
    rejected: 'danger',
    completed: 'info'
  }
  return types[status] || ''
}

// 显示详情
const showDetails = (transfer) => {
  currentTransfer.value = transfer
  detailVisible.value = true
}

// 处理审核
const handleReview = async (id, status) => {
  try {
    await ElMessageBox.confirm(
        `确定要${status === 'approved' ? '批准' : '拒绝'}该转会申请吗？`,
        '审核确认',
        {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }
    )

    loading.value = true
    await transfersStore.reviewTransfer({ id, status })
    ElMessage.success('审核操作成功')
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('操作失败: ' + (error.message || '未知错误'))
    }
  } finally {
    loading.value = false
  }
}

// 初始化加载数据
onMounted(async () => {
  loading.value = true
  try {
    await transfersStore.fetchTransfers()
  } catch (error) {
    ElMessage.error('加载转会数据失败')
  } finally {
    loading.value = false
  }
})
</script>

<style scoped>
.review-transfer-container {
  padding: 20px;
}

.el-table {
  margin-top: 20px;
}

.el-button + .el-button {
  margin-left: 8px;
}
</style>