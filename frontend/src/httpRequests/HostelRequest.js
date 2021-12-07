import axios from 'axios';

const HOSTEL_API_BASE_URL= "http://localhost:8080/hostel";

class HostelRequest {
    // Get a complete list of hostels from the server
    getHostelList() {
        return axios.get(HOSTEL_API_BASE_URL);
    }
    // getIndividualHostel(id) {
    //     return axios.get(HOSTEL_API_BASE_URL + "/" + id);
    // }
    // postHostelComment() {

    // }
}

export default new HostelRequest();
