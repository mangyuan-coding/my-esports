<template>
  <el-aside class="app-sidebar" width="200px">
    <el-menu
        :default-active="activeMenu"
        router
        class="sidebar-menu"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
    >
      <el-menu-item index="/dashboard">
        <el-icon><i-ep-house /></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-sub-menu index="players">
        <template #title>
          <el-icon><i-ep-user /></el-icon>
          <span>选手管理</span>
        </template>
        <el-menu-item index="/players">选手列表</el-menu-item>
        <el-menu-item index="/players/add">添加选手</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="clubs">
        <template #title>
          <el-icon><i-ep-office-building /></el-icon>
          <span>俱乐部管理</span>
        </template>
        <el-menu-item index="/clubs">俱乐部列表</el-menu-item>
        <el-menu-item index="/clubs/add">添加俱乐部</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="transfers">
        <template #title>
          <el-icon><i-ep-refresh /></el-icon>
          <span>转会管理</span>
        </template>
        <el-menu-item index="/transfers">转会列表</el-menu-item>
        <el-menu-item index="/transfers/submit">提交转会</el-menu-item>
        <el-menu-item v-if="user.isAdmin" index="/transfers/review">审核转会</el-menu-item>
      </el-sub-menu>
      <el-sub-menu index="matches">
        <template #title>
          <el-icon><i-ep-trophy /></el-icon>
          <span>赛事管理</span>
        </template>
        <el-menu-item index="/matches">赛事列表</el-menu-item>
        <el-menu-item index="/matches/add">添加赛事</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </el-aside>
</template>

<script setup>
import { computed } from 'vue'
import { useRoute } from 'vue-router'
import { useAuthStore } from '@/stores/auth'

const route = useRoute()
const authStore = useAuthStore()

const user = authStore.user

const activeMenu = computed(() => {
  const { path } = route
  return path
})
</script>

<style scoped>
.app-sidebar {
  background-color: #545c64;
  height: 100vh;
}

.sidebar-menu {
  border-right: none;
}
</style>