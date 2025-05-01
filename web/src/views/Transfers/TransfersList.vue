<template>
  <div class="transfers-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h2>转会列表</h2>
          <el-button
              type="primary"
              @click="$router.push('/transfers/submit')"
              v-if="$route.name !== 'ReviewTransfer'"
          >
            提交转会申请
          </el-button>
        </div>
      </template>

      <el-table :data="transfers" style="width: 100%">
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
        <el-table-column prop="transferFee" label="转会费" />
        <el-table-column prop="status" label="状态">
          <template #default="{ row }">
            <el-tag :type="statusTagType(row.status)">
              {{ row.status }}
            </el-tag>
          </template>
        </el-table-column>
        <el-table-column label="操作" width="180" v-if="$route.name === 'ReviewTransfer'">
          <template #default="{ row }">
            <el-button size="small" @click="handleReview(row.id, 'approved')">通过</el-button>
            <el-button size="small" type="danger" @click="handleReview(row.id, 'rejected')">拒绝</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useTransfersStore } from '@/stores/transfers'
import { ElMessage } from 'element-plus'

const transfersStore = useTransfersStore()
const transfers = ref([])

const statusTagType = (status) => {
  const map = {
    pending: 'warning',
    approved: 'success',
    rejected: 'danger'
  }
  return map[status] || ''
}

const handleReview = async (id, status) => {
  try {
    await transfersStore.reviewTransfer({ id, status })
    ElMessage.success('操作成功')
    fetchTransfers()
  } catch (error) {
    ElMessage.error('操作失败')
  }
}

const fetchTransfers = async () => {
  await transfersStore.fetchTransfers()
  transfers.value = transfersStore.transfers
}

onMounted(() => {
  fetchTransfers()
})
</script>

<style scoped>
.transfers-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>