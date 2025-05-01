import axios from 'axios'

const API_URL = '/api/matches'

/**
 * 获取所有赛事列表
 * @param {Object} [params] - 查询参数
 * @returns {Promise<Array>}
 */
export const getMatches = async (params = {}) => {
    try {
        const response = await axios.get(API_URL, { params })
        return response.data
    } catch (error) {
        console.error('获取赛事列表失败:', error)
        throw error
    }
}

/**
 * 获取赛事详情
 * @param {number} id - 赛事ID
 * @returns {Promise<Object>}
 */
export const getMatchById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/${id}`)
        return response.data
    } catch (error) {
        console.error('获取赛事详情失败:', error)
        throw error
    }
}

/**
 * 创建新赛事
 * @param {Object} matchData - 赛事数据
 * @returns {Promise<Object>}
 */
export const createMatch = async (matchData) => {
    try {
        const response = await axios.post(API_URL, matchData)
        return response.data
    } catch (error) {
        console.error('创建赛事失败:', error)
        throw error
    }
}

/**
 * 更新赛事信息
 * @param {number} id - 赛事ID
 * @param {Object} matchData - 更新数据
 * @returns {Promise<Object>}
 */
export const updateMatch = async (id, matchData) => {
    try {
        const response = await axios.put(`${API_URL}/${id}`, matchData)
        return response.data
    } catch (error) {
        console.error('更新赛事失败:', error)
        throw error
    }
}

/**
 * 删除赛事
 * @param {number} id - 赛事ID
 * @returns {Promise<void>}
 */
export const deleteMatch = async (id) => {
    try {
        await axios.delete(`${API_URL}/${id}`)
    } catch (error) {
        console.error('删除赛事失败:', error)
        throw error
    }
}

/**
 * 报名参赛
 * @param {number} matchId - 赛事ID
 * @param {number} clubId - 俱乐部ID
 * @returns {Promise<Object>}
 */
export const registerMatch = async (matchId, clubId) => {
    try {
        const response = await axios.post(`${API_URL}/${matchId}/register`, { clubId })
        return response.data
    } catch (error) {
        console.error('报名参赛失败:', error)
        throw error
    }
}