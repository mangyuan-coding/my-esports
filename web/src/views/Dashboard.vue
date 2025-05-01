<template>
  <div class="dashboard">
    <el-row :gutter="20">
      <el-col :span="8">
        <el-card>
          <div slot="header">
            <span>选手统计</span>
          </div>
          <div class="card-body">
            <h3>{{ playerCount }}</h3>
            <p>已注册选手</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div slot="header">
            <span>俱乐部统计</span>
          </div>
          <div class="card-body">
            <h3>{{ clubCount }}</h3>
            <p>已注册俱乐部</p>
          </div>
        </el-card>
      </el-col>
      <el-col :span="8">
        <el-card>
          <div slot="header">
            <span>转会统计</span>
          </div>
          <div class="card-body">
            <h3>{{ transferCount }}</h3>
            <p>待处理转会</p>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="mt-20">
      <el-col :span="12">
        <el-card>
          <div slot="header">
            <span>最近转会</span>
          </div>
          <el-table :data="recentTransfers" style="width: 100%">
            <el-table-column prop="player.name" label="选手" />
            <el-table-column prop="fromClub.name" label="原俱乐部" />
            <el-table-column prop="toClub.name" label="目标俱乐部" />
            <el-table-column prop="status" label="状态" />
          </el-table>
        </el-card>
      </el-col>
      <el-col :span="12">
        <el-card>
          <div slot="header">
            <span>即将开始的赛事</span>
          </div>
          <el-table :data="upcomingMatches" style="width: 100%">
            <el-table-column prop="name" label="赛事名称" />
            <el-table-column prop="game" label="游戏" />
            <el-table-column prop="startDate" label="开始时间" />
          </el-table>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { usePlayersStore } from '@/stores/players'
import { useClubsStore } from '@/stores/clubs'
import { useTransfersStore } from '@/stores/transfers'
import { useMatchesStore } from '@/stores/matches'

const playersStore = usePlayersStore()
const clubsStore = useClubsStore()
const transfersStore = useTransfersStore()
const matchesStore = useMatchesStore()

const playerCount = ref(0)
const clubCount = ref(0)
const transferCount = ref(0)
const recentTransfers = ref([])
const upcomingMatches = ref([])

onMounted(async () => {
  await Promise.all([
    playersStore.fetchPlayers(),
    clubsStore.fetchClubs(),
    transfersStore.fetchTransfers(),
    matchesStore.fetchMatches()
  ])

  playerCount.value = playersStore.players.length
  clubCount.value = clubsStore.clubs.length
  transferCount.value = transfersStore.transfers.filter(t => t.status === 'pending').length
  recentTransfers.value = [...transfersStore.transfers].slice(0, 5)
  upcomingMatches.value = matchesStore.matches
      .filter(m => new Date(m.startDate) > new Date())
      .sort((a, b) => new Date(a.startDate) - new Date(b.startDate))
      .slice(0, 5)
})
</script>

<style scoped>
.dashboard {
  padding: 20px;
}
.card-body {
  text-align: center;
}
.card-body h3 {
  font-size: 24px;
  margin-bottom: 10px;
}
.mt-20 {
  margin-top: 20px;
}
</style>