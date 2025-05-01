import axios from 'axios'

const API_URL = '/api/players'

const getPlayers = async () => {
    const response = await axios.get(API_URL)
    return response.data
}

const getPlayerById = async (id) => {
    const response = await axios.get(`${API_URL}/${id}`)
    return response.data
}

const createPlayer = async (playerData) => {
    const response = await axios.post(API_URL, playerData)
    return response.data
}

const updatePlayer = async ({ id, ...playerData }) => {
    const response = await axios.put(`${API_URL}/${id}`, playerData)
    return response.data
}

const deletePlayer = async (id) => {
    const response = await axios.delete(`${API_URL}/${id}`)
    return response.data
}

export {
    getPlayers,
    getPlayerById,
    createPlayer,
    updatePlayer,
    deletePlayer
}