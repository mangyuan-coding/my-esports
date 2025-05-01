import { defineStore } from 'pinia'
import {
    getPlayers,
    getPlayerById,
    createPlayer,
    updatePlayer,
    deletePlayer
} from '@/api/players'

export const usePlayersStore = defineStore('players', {
    state: () => ({
        players: [],
        currentPlayer: null
    }),
    actions: {
        async fetchPlayers() {
            try {
                const response = await getPlayers()
                this.players = response.data
            } catch (error) {
                console.error('获取选手列表失败:', error)
                throw error
            }
        },
        async fetchPlayer(id) {
            try {
                const response = await getPlayerById(id)
                this.currentPlayer = response.data
            } catch (error) {
                console.error('获取选手详情失败:', error)
                throw error
            }
        },
        async addPlayer(playerData) {
            try {
                const response = await createPlayer(playerData)
                this.players.push(response.data)
                return response.data
            } catch (error) {
                console.error('添加选手失败:', error)
                throw error
            }
        },
        async updatePlayer({ id, ...playerData }) {
            try {
                const response = await updatePlayer({ id, ...playerData })
                const index = this.players.findIndex(p => p.id === id)
                if (index !== -1) {
                    this.players.splice(index, 1, response.data)
                }
                return response.data
            } catch (error) {
                console.error('更新选手失败:', error)
                throw error
            }
        },
        async removePlayer(id) {
            try {
                await deletePlayer(id)
                this.players = this.players.filter(p => p.id !== id)
            } catch (error) {
                console.error('删除选手失败:', error)
                throw error
            }
        }
    }
})