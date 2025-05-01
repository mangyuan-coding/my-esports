<template>
  <div class="matches-list-container">
    <el-card>
      <template #header>
        <div class="card-header">
          <h2>赛事列表</h2>
          <el-button
              type="primary"
              @click="$router.push('/matches/add')"
          >
            创建新赛事
          </el-button>
        </div>
      </template>

      <el-tabs v-model="activeTab">
        <el-tab-pane label="全部赛事" name="all">
          <match-table :data="matchesStore.matches" />
        </el-tab-pane>
        <el-tab-pane label="即将开始" name="upcoming">
          <match-table :data="matchesStore.upcomingMatches" />
        </el-tab-pane>
        <el-tab-pane label="已结束" name="completed">
          <match-table :data="matchesStore.pastMatches" />
        </el-tab-pane>
      </el-tabs>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useMatchesStore } from '@/stores/matches'
import MatchTable from './components/MatchTable.vue'

const matchesStore = useMatchesStore()
const activeTab = ref('all')

onMounted(async () => {
  await matchesStore.fetchMatches()
})
</script>

<style scoped>
.matches-list-container {
  padding: 20px;
}
.card-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
</style>