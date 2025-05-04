import axios from 'axios'

const API_URL = import.meta.env.VITE_API_URL

const getPlayers = async () => {
    const response = await axios.get(`${API_URL}/players`)
    return response.data
}

const getPlayerById = async (id) => {
    const response = await axios.get(`${API_URL}/players/${id}`)
    return response.data
}

const createPlayer = async (playerData) => {
    const response = await axios.post(`${API_URL}/players`, playerData)
    return response.data
}

const updatePlayer = async ({ id, ...playerData }) => {
    const response = await axios.put(`${API_URL}/players/${id}`, playerData)
    return response.data
}

const deletePlayer = async (id) => {
    const response = await axios.delete(`${API_URL}/players/${id}`)
    return response.data
}

export {
    getPlayers,
    getPlayerById,
    createPlayer,
    updatePlayer,
    deletePlayer
}