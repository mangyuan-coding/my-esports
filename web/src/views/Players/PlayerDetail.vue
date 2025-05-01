<template>
  <div class="match-detail-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h2>{{ match.name }}</h2>
          <div>
            <el-button
                type="primary"
                @click="$router.push(`/matches/${match.id}/edit`)"
            >
              编辑
            </el-button>
            <el-button
                type="danger"
                @click="handleDelete"
            >
              删除
            </el-button>
          </div>
        </div>
      </template>

      <el-descriptions :column="2" border>
        <el-descriptions-item label="游戏类型">{{ match.game }}</el-descriptions-item>
        <el-descriptions-item label="开始时间">{{ formatDate(match.startDate) }}</el-descriptions-item>
        <el-descriptions-item label="结束时间">{{ formatDate(match.endDate) }}</el-descriptions-item>
        <el-descriptions-item label="奖金池">{{ match.prizePool }}</el-descriptions-item>
        <el-descriptions-item label="举办地">{{ match.location }}</el-descriptions-item>
        <el-descriptions-item label="赛事状态">
          <el-tag :type="matchStatusType(match.status)">
            {{ match.status }}
          </el-tag>
        </el-descriptions-item>
        <el-descriptions-item label="赛事描述" :span="2">{{ match.description }}</el-descriptions-item>
      </el-descriptions>

      <el-divider />

      <h3>参赛俱乐部 ({{ participants.length }})</h3>
      <el-table :data="participants" style="width: 100%">
        <el-table-column prop="club.name" label="俱乐部" />
        <el-table-column prop="registerDate" label="报名时间">
          <template #default="{ row }">
            {{ formatDate(row.registerDate) }}
          </template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template #default="{ row }">
            <el-button
                size="small"
                type="danger"
                @click="handleUnregister(row.id)"
            >
              取消报名
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useMatchesStore } from '@/stores/matches'
import { ElMessage, ElMessageBox } from 'element-plus'
import { formatDate } from '@/utils/date'

const route = useRoute()
const router = useRouter()
const matchesStore = useMatchesStore()

const match = ref({
  name: '',
  game: '',
  startDate: '',
  endDate: '',
  prizePool: '',
  location: '',
  status: 'upcoming',
  description: ''
})

const participants = ref([])

const matchStatusType = (status) => {
  const types = {
    upcoming: 'warning',
    ongoing: 'success',
    completed: 'info',
    canceled: 'danger'
  }
  return types[status] || ''
}

const fetchMatchDetail = async () => {
  try {
    await matchesStore.fetchMatch(route.params.id)
    match.value = matchesStore.currentMatch
    participants.value = matchesStore.currentMatch.participants || []
  } catch (error) {
    ElMessage.error('获取赛事详情失败')
  }
}

const handleDelete = async () => {
  try {
    await ElMessageBox.confirm('确定删除该赛事吗？', '警告', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })
    await matchesStore.deleteMatchById(route.params.id)
    ElMessage.success('删除成功')
    router.push('/matches')
  } catch (error) {
    if (error !== 'cancel') {
      ElMessage.error('删除失败')
    }
  }
}

const handleUnregister = async (registrationId) => {
  // 实现取消报名逻辑
}

onMounted(() => {
  fetchMatchDetail()
})
</script>

<style scoped>
.match-detail-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>