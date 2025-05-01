<template>
  <div class="match-form-container">
    <el-card>
      <template #header>
        <h2>{{ formTitle }}</h2>
      </template>

      <el-form
          :model="form"
          :rules="rules"
          ref="formRef"
          label-width="120px"
          label-position="top"
      >
        <el-form-item label="赛事名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入赛事名称" />
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

        <el-form-item label="赛事时间" required>
          <el-col :span="11">
            <el-form-item prop="startDate">
              <el-date-picker
                  v-model="form.startDate"
                  type="datetime"
                  placeholder="开始时间"
                  style="width: 100%"
              />
            </el-form-item>
          </el-col>
          <el-col :span="2" class="text-center">-</el-col>
          <el-col :span="11">
            <el-form-item prop="endDate">
              <el-date-picker
                  v-model="form.endDate"
                  type="datetime"
                  placeholder="结束时间"
                  style="width: 100%"
              />
            </el-form-item>
          </el-col>
        </el-form-item>

        <el-form-item label="奖金池" prop="prizePool">
          <el-input v-model="form.prizePool" placeholder="例如: $100,000">
            <template #prepend>$</template>
          </el-input>
        </el-form-item>

        <el-form-item label="举办地" prop="location">
          <el-input v-model="form.location" placeholder="请输入举办城市" />
        </el-form-item>

        <el-form-item label="赛事状态" prop="status">
          <el-radio-group v-model="form.status">
            <el-radio-button label="upcoming">即将开始</el-radio-button>
            <el-radio-button label="ongoing">进行中</el-radio-button>
            <el-radio-button label="completed">已结束</el-radio-button>
          </el-radio-group>
        </el-form-item>

        <el-form-item label="赛事描述" prop="description">
          <el-input
              v-model="form.description"
              type="textarea"
              :rows="4"
              placeholder="请输入赛事描述"
          />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="$router.push('/matches')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useMatchesStore } from '@/stores/matches'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const matchesStore = useMatchesStore()
const formRef = ref(null)

const isEdit = computed(() => route.name === 'EditMatch')
const formTitle = computed(() => isEdit.value ? '编辑赛事' : '创建新赛事')

const form = ref({
  name: '',
  game: '',
  startDate: '',
  endDate: '',
  prizePool: '',
  location: '',
  status: 'upcoming',
  description: ''
})

const rules = {
  name: [{ required: true, message: '请输入赛事名称', trigger: 'blur' }],
  game: [{ required: true, message: '请选择游戏类型', trigger: 'change' }],
  startDate: [{ required: true, message: '请选择开始时间', trigger: 'change' }],
  endDate: [{
    required: true,
    validator: (rule, value, callback) => {
      if (!value) {
        callback(new Error('请选择结束时间'))
      } else if (new Date(value) <= new Date(form.value.startDate)) {
        callback(new Error('结束时间必须晚于开始时间'))
      } else {
        callback()
      }
    },
    trigger: 'change'
  }]
}

const gameOptions = [
  { value: 'LOL', label: '英雄联盟' },
  { value: 'DOTA2', label: 'DOTA2' },
  { value: 'CSGO', label: 'CS:GO' },
  { value: 'Valorant', label: '无畏契约' },
  { value: 'Overwatch', label: '守望先锋' }
]

const submitForm = async () => {
  try {
    await formRef.value.validate()

    if (isEdit.value) {
      await matchesStore.updateMatchData(route.params.id, form.value)
      ElMessage.success('赛事更新成功')
    } else {
      await matchesStore.createNewMatch(form.value)
      ElMessage.success('赛事创建成功')
    }

    router.push('/matches')
  } catch (error) {
    console.error('表单提交失败:', error)
  }
}

onMounted(async () => {
  if (isEdit.value) {
    await matchesStore.fetchMatch(route.params.id)
    form.value = { ...matchesStore.currentMatch }
  }
})
</script>

<style scoped>
.match-form-container {
  padding: 20px;
}
.text-center {
  text-align: center;
}
</style>