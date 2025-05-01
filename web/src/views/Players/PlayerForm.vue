<template>
  <div class="player-form">
    <el-card>
      <template #header>
        <h2>{{ formTitle }}</h2>
      </template>

      <el-form
          :model="form"
          :rules="rules"
          ref="playerForm"
          label-width="120px"
          label-position="top"
      >
        <el-form-item label="选手姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入选手姓名" />
        </el-form-item>

        <el-form-item label="游戏类型" prop="game">
          <el-select v-model="form.game" placeholder="请选择游戏" style="width: 100%">
            <el-option
                v-for="game in gameOptions"
                :key="game.value"
                :label="game.label"
                :value="game.value"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="位置/角色" prop="position">
          <el-input v-model="form.position" placeholder="请输入位置/角色" />
        </el-form-item>

        <el-form-item label="所属俱乐部" prop="clubId">
          <el-select v-model="form.clubId" placeholder="请选择俱乐部" style="width: 100%">
            <el-option
                v-for="club in clubs"
                :key="club.id"
                :label="club.name"
                :value="club.id"
            />
          </el-select>
        </el-form-item>

        <el-form-item label="加入日期" prop="joinDate">
          <el-date-picker
              v-model="form.joinDate"
              type="date"
              placeholder="选择日期"
              style="width: 100%"
          />
        </el-form-item>

        <el-form-item label="选手简介" prop="bio">
          <el-input v-model="form.bio" type="textarea" :rows="4" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">{{ isEdit ? '更新' : '创建' }}</el-button>
          <el-button @click="$router.push('/players')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { usePlayersStore } from '@/stores/players'
import { useClubsStore } from '@/stores/clubs'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const playersStore = usePlayersStore()
const clubsStore = useClubsStore()

const isEdit = computed(() => route.name === 'EditPlayer')
const formTitle = computed(() => isEdit.value ? '编辑选手信息' : '添加新选手')

const form = ref({
  name: '',
  game: '',
  position: '',
  clubId: '',
  joinDate: new Date(),
  bio: ''
})

const rules = {
  name: [{ required: true, message: '请输入选手姓名', trigger: 'blur' }],
  game: [{ required: true, message: '请选择游戏类型', trigger: 'change' }],
  position: [{ required: true, message: '请输入位置/角色', trigger: 'blur' }],
  clubId: [{ required: true, message: '请选择俱乐部', trigger: 'change' }]
}

const gameOptions = [
  { value: 'LOL', label: '英雄联盟' },
  { value: 'DOTA2', label: 'DOTA 2' },
  { value: 'CSGO', label: 'CS:GO' },
  { value: 'Overwatch', label: '守望先锋' },
  { value: 'Valorant', label: '无畏契约' }
]

const clubs = ref([])
const playerForm = ref(null)

onMounted(async () => {
  await clubsStore.fetchClubs()
  clubs.value = clubsStore.clubs

  if (isEdit.value) {
    await playersStore.fetchPlayer(route.params.id)
    form.value = { ...playersStore.currentPlayer }
  }
})

const submitForm = async () => {
  try {
    await playerForm.value.validate()

    if (isEdit.value) {
      await playersStore.updatePlayer({
        id: route.params.id,
        ...form.value
      })
      ElMessage.success('选手信息更新成功')
    } else {
      await playersStore.addPlayer(form.value)
      ElMessage.success('选手添加成功')
    }

    router.push('/players')
  } catch (error) {
    console.error('表单提交失败:', error)
  }
}
</script>

<style scoped>
.player-form {
  padding: 20px;
}
</style>