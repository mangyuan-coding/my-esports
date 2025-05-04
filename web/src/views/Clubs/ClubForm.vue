<template>
  <div class="club-form-container">
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
        <el-form-item label="俱乐部名称" prop="name">
          <el-input v-model="form.name" placeholder="请输入俱乐部名称" />
        </el-form-item>

        <el-form-item label="所在地" prop="location">
          <el-input v-model="form.location" placeholder="请输入所在地" />
        </el-form-item>

        <el-form-item label="成立时间" prop="establishedDate">
          <el-date-picker
              v-model="form.establishedDate"
              type="date"
              placeholder="选择日期"
              value-format="YYYY-MM-DD"
          />
        </el-form-item>

        <el-form-item label="主教练" prop="coach">
          <el-input v-model="form.coach" placeholder="请输入主教练姓名" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="submitForm">提交</el-button>
          <el-button @click="$router.push('/clubs')">取消</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, computed, onMounted } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import { useClubsStore } from '@/stores/clubs'
import { ElMessage } from 'element-plus'

const route = useRoute()
const router = useRouter()
const clubsStore = useClubsStore()
const formRef = ref(null)

const isEdit = computed(() => route.name === 'EditClub')
const formTitle = computed(() => isEdit.value ? '编辑俱乐部' : '新增俱乐部')

const form = ref({
  name: '',
  location: '',
  establishedDate: '',
  coach: '',
  description: ''
})

const rules = {
  name: [{ required: true, message: '请输入俱乐部名称', trigger: 'blur' }],
  location: [{ required: true, message: '请输入所在地', trigger: 'blur' }]
}

onMounted(async () => {
  if (isEdit.value) {
    await clubsStore.fetchClub(route.params.id)
    form.value = { ...clubsStore.currentClub }
  }
})

const submitForm = async () => {
  try {
    await formRef.value.validate()
    
    if (isEdit.value) {
      await clubsStore.updateClubData(
        route.params.id,
        form.value
      )
      ElMessage.success('选手信息更新成功')
    } else {
      await clubsStore.createNewClub(form.value)
      ElMessage.success('选手添加成功')
    }

    router.push('/clubs')
  } catch (error) {
    console.error('表单验证失败:', error)
  }
}
</script>

<style scoped>
.club-form-container {
  padding: 20px;
}
</style>