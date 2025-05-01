import express from 'express'
import bodyParser from 'body-parser'
import cors from 'cors'

const app = express()
app.use(cors())
app.use(bodyParser.json())

// 模拟数据库
let users = [
    { id: 1, username: 'admin', password: 'admin123', isAdmin: true }
]

let players = [
    {
        id: 1,
        name: '张三',
        game: 'LOL',
        position: '中单',
        clubId: 1,
        joinDate: '2022-01-15',
        bio: '擅长法师英雄，曾获得多个赛事MVP'
    }
]

let clubs = [
    {
        id: 1,
        name: 'EDG电子竞技俱乐部',
        establishedDate: '2013-09-13',
        location: '上海',
        coach: '明凯',
        sponsor: '合创汽车',
        description: '中国知名电子竞技俱乐部'
    }
]

let transfers = [
    {
        id: 1,
        playerId: 1,
        fromClubId: 1,
        toClubId: 2,
        transferFee: 500000,
        transferDate: '2023-06-01',
        status: 'pending',
        notes: '常规转会'
    }
]

let matches = [
    {
        id: 1,
        name: '2023英雄联盟全球总决赛',
        game: 'LOL',
        startDate: '2023-10-05',
        endDate: '2023-11-05',
        prizePool: '225万美元',
        location: '韩国首尔'
    }
]

// 认证API
app.post('/api/auth/login', (req, res) => {
    const { username, password } = req.body
    const user = users.find(u => u.username === username && u.password === password)

    if (user) {
        res.json({
            data: {
                ...user,
                token: `mock-token-${Date.now()}`
            }
        })
    } else {
        res.status(401).json({ error: '用户名或密码错误' })
    }
})

// 选手API
app.get('/api/players', (req, res) => {
    res.json({ data: players.map(p => ({
            ...p,
            club: clubs.find(c => c.id === p.clubId)
        }))})
})

// 启动服务器
app.listen(3001, () => {
    console.log('Mock server running on http://localhost:3001')
})