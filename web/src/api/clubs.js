import axios from 'axios'

const API_URL = `${import.meta.env.VITE_API_URL}`

/**
 * 获取所有俱乐部列表
 * @returns {Promise<Array>}
 */
export const getClubs = async () => {
    try {
        const response = await axios.get(`${API_URL}/clubs`)
        return response.data
    } catch (error) {
        console.error('获取俱乐部列表失败:', error)
        throw error
    }
}

/**
 * 获取俱乐部详情
 * @param {number} id - 俱乐部ID
 * @returns {Promise<Object>}
 */
export const getClubById = async (id) => {
    try {
        const response = await axios.get(`${API_URL}/clubs/${id}`)
        return response.data
    } catch (error) {
        console.error('获取俱乐部详情失败:', error)
        throw error
    }
}

/**
 * 创建新俱乐部
 * @param {Object} clubData - 俱乐部数据
 * @returns {Promise<Object>}
 */
export const createClub = async (clubData) => {
    try {
        const response = await axios.post(`${API_URL}/clubs`, clubData)
        return response.data
    } catch (error) {
        console.error('创建俱乐部失败:', error)
        throw error
    }
}

/**
 * 更新俱乐部信息
 * @param {number} id - 俱乐部ID
 * @param {Object} clubData - 更新数据
 * @returns {Promise<Object>}
 */
export const updateClub = async (id, clubData) => {
    try {
        const response = await axios.put(`${API_URL}/clubs/${id}`, clubData)
        return response.data
    } catch (error) {
        console.error('更新俱乐部失败:', error)
        throw error
    }
}

/**
 * 删除俱乐部
 * @param {number} id - 俱乐部ID
 * @returns {Promise<void>}
 */
export const deleteClub = async (id) => {
    try {
        await axios.delete(`${API_URL}/clubs/${id}`)
    } catch (error) {
        console.error('删除俱乐部失败:', error)
        throw error
    }
}

/**
 * 获取俱乐部成员列表
 * @param {number} clubId - 俱乐部ID
 * @returns {Promise<Array>}
 */
export const getClubMembers = async (clubId) => {
    try {
        const response = await axios.get(`${API_URL}/clubs/${clubId}/members`)
        return response.data
    } catch (error) {
        console.error('获取俱乐部成员失败:', error)
        throw error
    }
}