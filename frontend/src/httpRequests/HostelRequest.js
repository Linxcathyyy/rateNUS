import axios from 'axios';

const HOSTEL_API_BASE_URL= "http://localhost:8080/hostel";

class HostelRequest {
    // Get a complete list of hostels from the server
    getHostelList() {
        return axios.get(HOSTEL_API_BASE_URL);
    }
    // Get individual hostel data from server
    getIndividualHostel(hostelId) {
        return axios.get(HOSTEL_API_BASE_URL + "/" + hostelId);
    }
    // postHostelComment() {

    // }

    findHostels(keyword) {
        return axios.post(HOSTEL_API_BASE_URL, {
            keyword: keyword
        });
    }
}

export default new HostelRequest();
