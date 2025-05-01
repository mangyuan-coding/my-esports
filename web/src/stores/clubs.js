import { defineStore } from 'pinia'
import {
    getClubs,
    getClubById,
    createClub,
    updateClub,
    deleteClub,
    getClubMembers
} from '@/api/clubs'

export const useClubsStore = defineStore('clubs', {
    state: () => ({
        clubs: [],
        currentClub: null,
        members: [],
        loading: false,
        error: null
    }),
    actions: {
        async fetchClubs() {
            this.loading = true
            try {
                const response = await getClubs()
                this.clubs = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取俱乐部列表失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async fetchClub(id) {
            this.loading = true
            try {
                const response = await getClubById(id)
                this.currentClub = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取俱乐部详情失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async createNewClub(clubData) {
            this.loading = true
            try {
                const response = await createClub(clubData)
                this.clubs.push(response.data)
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '创建俱乐部失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async updateClubData(id, clubData) {
            this.loading = true
            try {
                const response = await updateClub(id, clubData)
                const index = this.clubs.findIndex(c => c.id === id)
                if (index !== -1) {
                    this.clubs.splice(index, 1, response.data)
                }
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '更新俱乐部失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async deleteClubById(id) {
            this.loading = true
            try {
                await deleteClub(id)
                this.clubs = this.clubs.filter(c => c.id !== id)
                this.error = null
            } catch (error) {
                this.error = error.message || '删除俱乐部失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async fetchClubMembers(clubId) {
            this.loading = true
            try {
                const response = await getClubMembers(clubId)
                this.members = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取俱乐部成员失败'
                throw error
            } finally {
                this.loading = false
            }
        }
    },
    getters: {
        getClubById: (state) => (id) => {
            return state.clubs.find(c => c.id === id)
        },
        activeClubs: (state) => {
            return state.clubs.filter(c => c.status === 'active')
        }
    }
})