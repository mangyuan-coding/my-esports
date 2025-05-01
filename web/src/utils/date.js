// src/utils/date.js
/**
 * 格式化日期
 * @param {Date|string} date - 日期对象或字符串
 * @param {string} [format='YYYY-MM-DD'] - 格式模板
 * @returns {string} 格式化后的日期
 */
export const formatDate = (date, format = 'YYYY-MM-DD') => {
    if (!date) return 'N/A'

    const d = new Date(date)
    if (isNaN(d.getTime())) return 'Invalid Date'

    const pad = num => num.toString().padStart(2, '0')

    return format
        .replace('YYYY', d.getFullYear())
        .replace('MM', pad(d.getMonth() + 1))
        .replace('DD', pad(d.getDate()))
        .replace('HH', pad(d.getHours()))
        .replace('mm', pad(d.getMinutes()))
        .replace('ss', pad(d.getSeconds()))
}

/**
 * 计算相对时间（如"3天前"）
 * @param {Date|string} date*/