<template>
  <div class="players-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h2>选手列表</h2>
          <el-button type="primary" @click="$router.push('/players/add')">添加选手</el-button>
        </div>
      </template>

      <el-table :data="players" style="width: 100%">
        <el-table-column prop="id" label="ID" width="80" />
        <el-table-column prop="name" label="姓名" />
        <el-table-column prop="game" label="游戏" />
        <el-table-column prop="position" label="位置" />
        <el-table-column prop="club.name" label="俱乐部" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button size="small" @click="$router.push(`/players/${scope.row.id}/edit`)">编辑</el-button>
            <el-button size="small" type="danger" @click="handleDelete(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { getPlayers, deletePlayer } from '@/api/players'

const players = ref([])

const fetchPlayers = async () => {
  try {
    const response = await getPlayers()
    players.value = response.data
  } catch (error) {
    console.error('获取选手列表失败:', error)
  }
}

const handleDelete = async (id) => {
  try {
    await deletePlayer(id)
    await fetchPlayers()
    ElMessage.success('删除成功')
  } catch (error) {
    console.error('删除选手失败:', error)
    ElMessage.error('删除失败')
  }
}

onMounted(() => {
  fetchPlayers()
})
</script>

<style scoped>
.players-container {
  padding: 20px;
}

.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>