<template>
  <div class="clubs-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h2>俱乐部列表</h2>
          <el-button
              type="primary"
              @click="$router.push('/clubs/add')">
            添加俱乐部
          </el-button>
        </div>
      </template>

      <el-table :data="clubs" style="width: 100%">
        <el-table-column prop="name" label="俱乐部名称" />
        <el-table-column prop="location" label="所在地" />
        <el-table-column prop="coach" label="教练" />
        <el-table-column label="操作" width="180">
          <template #default="scope">
            <el-button
                size="small"
                @click="$router.push(`/clubs/${scope.row.id}`)">
              详情
            </el-button>
            <el-button
                size="small"
                @click="$router.push(`/clubs/${scope.row.id}/edit`)">
              编辑
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useClubsStore } from '@/stores/clubs'

const clubsStore = useClubsStore()
const clubs = ref([])

onMounted(async () => {
  await clubsStore.fetchClubs()
  clubs.value = clubsStore.clubs
})
</script>

<style scoped>
.clubs-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
</style>