<!-- src/views/Matches/components/MatchTable.vue -->
<template>
  <el-table :data="data" style="width: 100%">
    <el-table-column prop="name" label="赛事名称" />
    <el-table-column prop="game" label="游戏类型" />
    <el-table-column label="时间范围">
      <template #default="{ row }">
        {{ formatDate(row.startDate) }} - {{ formatDate(row.endDate) }}
      </template>
    </el-table-column>
    <el-table-column prop="prizePool" label="奖金池" />
    <el-table-column prop="status" label="状态">
      <template #default="{ row }">
        <el-tag :type="statusTagType(row.status)">
          {{ statusText[row.status] }}
        </el-tag>
      </template>
    </el-table-column>
    <el-table-column label="操作" width="180">
      <template #default="{ row }">
        <el-button
            size="small"
            @click="$router.push(`/matches/${row.id}`)"
        >
          详情
        </el-button>
        <el-button
            size="small"
            type="danger"
            @click="$emit('delete', row.id)"
        >
          删除
        </el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import { formatDate } from '@/utils/date'

defineProps({
  data: {
    type: Array,
    default: () => []
  }
})

const statusText = {
  upcoming: '即将开始',
  ongoing: '进行中',
  completed: '已结束',
  canceled: '已取消'
}

const statusTagType = (status) => {
  const types = {
    upcoming: 'warning',
    ongoing: 'success',
    completed: 'info',
    canceled: 'danger'
  }
  return types[status] || ''
}
</script>