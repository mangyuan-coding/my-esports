<template>
  <div class="transfer-container">
    <el-card>
      <template #header>
        <h2>提交转会申请</h2>
      </template>

      <el-form :model="form" :rules="rules" ref="transferForm" label-width="120px">
        <el-form-item label="选手" prop="playerId">
          <el-select v-model="form.playerId" placeholder="请选择选手" style="width: 100%">
            <el-option v-for="player in players" :key="player.id" :label="`${player.name} (${player.club.name})`"
              :value="player.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="目标俱乐部" prop="toClubId">
          <el-select v-model="form.toClubId" placeholder="请选择目标俱乐部" style="width: 100%">
            <el-option v-for="club in clubs" :key="club.id" :label="club.name" :value="club.id" />
          </el-select>
        </el-form-item>

        <el-form-item label="转会费" prop="transferFee">
          <el-input-number v-model="form.transferFee" :min="0" :step="1000" style="width: 100%" />
        </el-form-item>

        <el-form-item label="转会日期" prop="transferDate">
          <el-date-picker v-model="form.transferDate" type="date" placeholder="选择日期" style="width: 100%" />
        </el-form-item>

        <el-form-item label="备注" prop="notes">
          <el-input v-model="form.notes" type="textarea" :rows="3" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitTransferForm" :loading="loading">提交申请</el-button>
          <el-button @click="$router.push('/transfers')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { getPlayers } from '@/api/players'
import { getClubs } from '@/api/clubs'
import { submitTransfer } from '@/api/transfers'
import { ElMessage } from 'element-plus'

const form = ref({
  playerId: '',
  toClubId: '',
  transferFee: 0,
  transferDate: new Date(),
  notes: ''
})

const rules = {
  playerId: [{ required: true, message: '请选择选手', trigger: 'change' }],
  toClubId: [{ required: true, message: '请选择目标俱乐部', trigger: 'change' }],
  transferFee: [{ required: true, message: '请输入转会费', trigger: 'blur' }],
  transferDate: [{ required: true, message: '请选择转会日期', trigger: 'change' }]
}

const players = ref([])
const clubs = ref([])
const loading = ref(false)
const transferForm = ref(null)
const router = useRouter()

const fetchPlayers = async () => {
  try {
    const response = await getPlayers()
    players.value = response.data
  } catch (error) {
    console.error('获取选手列表失败:', error)
  }
}

const fetchClubs = async () => {
  try {
    const response = await getClubs()
    clubs.value = response.data
  } catch (error) {
    console.error('获取俱乐部列表失败:', error)
  }
}

const submitTransferForm = async () => {
  try {
    await transferForm.value.validate()
    loading.value = true
    const response = await submitTransfer(form.value)
    const { existed, playerNotExist, applyOwnClub } = response.data
    if (existed) {
      ElMessage.error('提交失败：转会申请已存在')
      throw new Error()
    }
    if (playerNotExist) {
      ElMessage.error('提交失败：选手不存在')
      throw new Error()
    }
    if (applyOwnClub) {
      ElMessage.error('提交失败：不能申请转会到自己的俱乐部')
      throw new Error()
    }
    ElMessage.success('转会申请提交成功')
    router.push('/transfers')
  } catch (error) {
    console.error('提交转会申请失败:')
  } finally {
    loading.value = false
  }
}

onMounted(() => {
  fetchPlayers()
  fetchClubs()
})
</script>

<style scoped>
.transfer-container {
  padding: 20px;
}
</style>