import axios from 'axios';

const HOSTEL_API_BASE_URL= "http://localhost:8080/hostel";

// Get a complete list of hostels from the server
class HostelRequest {
    getHostelList() {
        return axios.get(HOSTEL_API_BASE_URL);
    }
}

export default new HostelRequest();
