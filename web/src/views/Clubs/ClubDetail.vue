<template>
  <div class="club-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h2>{{ club.name }}</h2>
          <div>
            <el-button type="primary" @click="$router.push(`/clubs/${club.id}/edit`)">编辑</el-button>
            <el-button type="danger" @click="handleDelete">删除</el-button>
          </div>
        </div>
      </template>

      <el-descriptions :column="2" border>
        <el-descriptions-item label="成立时间">{{ formatDate(club.establishedDate) }}</el-descriptions-item>
        <el-descriptions-item label="所在地">{{ club.location }}</el-descriptions-item>
        <el-descriptions-item label="教练">{{ club.coach }}</el-descriptions-item>
        <el-descriptions-item label="赞助商">{{ club.sponsor }}</el-descriptions-item>
        <el-descriptions-item label="简介" :span="2">{{ club.description }}</el-descriptions-item>
      </el-descriptions>

      <h3 style="margin-top: 30px;">俱乐部成员</h3>
      <el-table :data="club.players" style="width: 100%">
        <el-table-column prop="name" label="选手姓名" />
        <el-table-column prop="game" label="游戏" />
        <el-table-column prop="position" label="位置" />
        <el-table-column prop="joinDate" label="加入时间" />
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { getClubById, deleteClub } from '@/api/clubs'
import { formatDate } from '@/utils/date'
import { ElMessage, ElMessageBox } from 'element-plus'

const route = useRoute()
const router = useRouter()

const club = ref({})

const fetchClub = async () => {
  try {
    const response = await getClubById(route.params.id)
    club.value = response.data
  } catch (error) {
    console.error('获取俱乐部详情失败:', error)
  }
}

const handleDelete = async () => {
  try {
    await ElMessageBox.confirm('确定要删除该俱乐部吗?', '提示', {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning'
    })

    await deleteClub(route.params.id)
    ElMessage.success('删除成功')
    router.push('/clubs')
  } catch (error) {
    if (error !== 'cancel') {
      console.error('删除俱乐部失败:', error)
      ElMessage.error('删除失败')
    }
  }
}

onMounted(() => {
  fetchClub()
})
</script>

<style scoped>
.club-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>