import { defineStore } from 'pinia'
import {
    getMatches,
    getMatchById,
    createMatch,
    updateMatch,
    deleteMatch,
    registerMatch
} from '@/api/matches'

export const useMatchesStore = defineStore('matches', {
    state: () => ({
        matches: [],
        currentMatch: null,
        registrations: [],
        loading: false,
        error: null
    }),
    actions: {
        async fetchMatches(params = {}) {
            this.loading = true
            try {
                const response = await getMatches(params)
                this.matches = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取赛事列表失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async fetchMatch(id) {
            this.loading = true
            try {
                const response = await getMatchById(id)
                this.currentMatch = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取赛事详情失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async createNewMatch(matchData) {
            this.loading = true
            try {
                const response = await createMatch(matchData)
                this.matches.push(response.data)
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '创建赛事失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async updateMatchData(id, matchData) {
            this.loading = true
            try {
                const response = await updateMatch(id, matchData)
                const index = this.matches.findIndex(m => m.id === id)
                if (index !== -1) {
                    this.matches.splice(index, 1, response.data)
                }
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '更新赛事失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async deleteMatchById(id) {
            this.loading = true
            try {
                await deleteMatch(id)
                this.matches = this.matches.filter(m => m.id !== id)
                this.error = null
            } catch (error) {
                this.error = error.message || '删除赛事失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async registerForMatch(matchId, clubId) {
            this.loading = true
            try {
                const response = await registerMatch(matchId, clubId)
                this.registrations.push(response.data)
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '报名参赛失败'
                throw error
            } finally {
                this.loading = false
            }
        }
    },
    getters: {
        upcomingMatches: (state) => {
            return state.matches.filter(m => new Date(m.startDate) > new Date())
        },
        pastMatches: (state) => {
            return state.matches.filter(m => new Date(m.endDate) < new Date())
        }
    }
})