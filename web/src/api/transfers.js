import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL

/**
 * 获取所有转会记录
 * @param {Object} [params] - 查询参数
 * @returns {Promise<Array>}
 */
export const getTransfers = async (params = {}) => {
    try {
        const response = await axios.get(`${API_URL}/transfers`, { params })
        return response.data
    } catch (error) {
        console.error('获取转会记录失败:', error)
        throw error
    }
}

/**
 * 获取转会详情
 * @param {number} id - 转会记录ID
 * @returns {Promise<Object>}
 */
export const getTransferById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/transfers/${id}`)
        return response.data
    } catch (error) {
        console.error('获取转会详情失败:', error)
        throw error
    }
}

/**
 * 提交转会申请
 * @param {Object} transferData - 转会数据
 * @returns {Promise<Object>}
 */
export const submitTransfer = async (transferData) => {
    try {
        const response = await axios.post(`${API_URL}/transfers`, transferData)
        return response.data
    } catch (error) {
        console.error('提交转会申请失败:', error)
        throw error
    }
}

/**
 * 审核转会申请
 * @param {number} id - 转会记录ID
 * @param {string} status - 审核状态 (approved/rejected)
 * @returns {Promise<Object>}
 */
export const reviewTransfer = async (id, status) => {
    try {
        const response = await axios.patch(`${API_URL}/transfers/${id}/review`, { status })
        return response.data
    } catch (error) {
        console.error('审核转会申请失败:', error)
        throw error
    }
}

/**
 * 完成转会签约
 * @param {number} id - 转会记录ID
 * @returns {Promise<Object>}
 */
export const completeTransfer = async (id) => {
    try {
        const response = await axios.post(`${API_URL}/transfers/${id}/complete`)
        return response.data
    } catch (error) {
        console.error('完成转会签约失败:', error)
        throw error
    }
}

/**
 * 获取选手转会历史
 * @param {number} playerId - 选手ID
 * @returns {Promise<Array>}
 */
export const getPlayerTransfers = async (playerId) => {
    try {
        const response = await axios.get(`${API_URL}/transfers/player/${playerId}`)
        return response.data
    } catch (error) {
        console.error('获取选手转会历史失败:', error)
        throw error
    }
}