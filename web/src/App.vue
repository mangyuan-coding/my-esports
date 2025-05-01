<template>
  <el-container>
    <app-header v-if="isAuthenticated" />
    <el-container>
      <app-sidebar v-if="isAuthenticated" />
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>

<script setup>
import { computed } from 'vue'
import { useAuthStore } from '@/stores/auth'
import AppHeader from '@/components/Layout/AppHeader.vue'
import AppSidebar from '@/components/Layout/AppSidebar.vue'

const authStore = useAuthStore()
const isAuthenticated = computed(() => authStore.isAuthenticated)
</script>

<style>
.el-container {
  min-height: 100vh;
}
</style>
