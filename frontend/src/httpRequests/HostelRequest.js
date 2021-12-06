import axios from 'axios';

// "https://jsonplaceholder.typicode.com/posts"
const HOSTEL_API_BASE_URL= "http://localhost:8080/hostel";

class HostelRequest {
    getHostelList() {
        return axios.get(HOSTEL_API_BASE_URL);
    }
}

export default new HostelRequest();
