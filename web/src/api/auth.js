import axios from 'axios'

const API_URL = '/api/auth'

const loginUser = async (credentials) => {
    const response = await axios.post(`${API_URL}/login`, credentials)
    return response.data
}

const registerUser = async (userData) => {
    const response = await axios.post(`${API_URL}/register`, userData)
    return response.data
}

const logoutUser = async () => {
    const response = await axios.post(`${API_URL}/logout`)
    return response.data
}

export { loginUser, registerUser, logoutUser }