import { defineStore } from 'pinia'
import {
    getTransfers,
    getTransferById,
    submitTransfer,
    reviewTransfer,
    completeTransfer,
    getPlayerTransfers
} from '@/api/transfers'

export const useTransfersStore = defineStore('transfers', {
    state: () => ({
        transfers: [],
        currentTransfer: null,
        playerTransfers: [],
        loading: false,
        error: null
    }),
    actions: {
        async fetchTransfers(params = {}) {
            this.loading = true
            try {
                const response = await getTransfers(params)
                this.transfers = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取转会记录失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async fetchTransfer(id) {
            this.loading = true
            try {
                const response = await getTransferById(id)
                this.currentTransfer = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取转会详情失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async submitNewTransfer(transferData) {
            this.loading = true
            try {
                const response = await submitTransfer(transferData)
                this.transfers.push(response.data)
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '提交转会申请失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async reviewTransferRequest(id, status) {
            this.loading = true
            try {
                const response = await reviewTransfer(id, status)
                const index = this.transfers.findIndex(t => t.id === id)
                if (index !== -1) {
                    this.transfers.splice(index, 1, response.data)
                }
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '审核转会申请失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async completeTransferProcess(id) {
            this.loading = true
            try {
                const response = await completeTransfer(id)
                const index = this.transfers.findIndex(t => t.id === id)
                if (index !== -1) {
                    this.transfers.splice(index, 1, response.data)
                }
                this.error = null
                return response.data
            } catch (error) {
                this.error = error.message || '完成转会失败'
                throw error
            } finally {
                this.loading = false
            }
        },

        async fetchPlayerTransfers(playerId) {
            this.loading = true
            try {
                const response = await getPlayerTransfers(playerId)
                this.playerTransfers = response.data
                this.error = null
            } catch (error) {
                this.error = error.message || '获取选手转会历史失败'
                throw error
            } finally {
                this.loading = false
            }
        }
    },
    getters: {
        pendingTransfers: (state) => {
            return state.transfers.filter(t => t.status === 'pending')
        },
        completedTransfers: (state) => {
            return state.transfers.filter(t => t.status === 'completed')
        }
    }
})