import axios from 'axios';

const API_SERVER_HOST = 'http://localhost:8090';
const prefix = `${API_SERVER_HOST}/book`;

export const getBookreco = async (genre) => {
    const res = await axios.get(`${prefix}/bookreco/${genre}`);
    return res.data;
}

