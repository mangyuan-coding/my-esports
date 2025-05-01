<template>
  <el-header class="app-header">
    <div class="header-left">
      <h1>电竞转会系统</h1>
    </div>
    <div class="header-right">
      <el-dropdown>
        <span class="el-dropdown-link">
          <el-avatar :size="40" :src="user.avatar || 'https://cube.elemecdn.com/3/7c/3ea6beec64369c2642b92c6726f1epng.png'" />
          <span class="username">{{ user.username }}</span>
        </span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="handleProfile">个人中心</el-dropdown-item>
            <el-dropdown-item @click="handleLogout" divided>退出登录</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </el-header>
</template>

<script setup>
import { useAuthStore } from '@/stores/auth'
import { useRouter } from 'vue-router'

const authStore = useAuthStore()
const router = useRouter()

const user = authStore.user

const handleProfile = () => {
  router.push('/profile')
}

const handleLogout = async () => {
  await authStore.logout()
}
</script>

<style scoped>
.app-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  background-color: #409EFF;
  color: white;
  padding: 0 20px;
}

.header-left h1 {
  margin: 0;
  font-size: 20px;
}

.el-dropdown-link {
  display: flex;
  align-items: center;
  cursor: pointer;
}

.username {
  margin-left: 10px;
  color: white;
}
</style>