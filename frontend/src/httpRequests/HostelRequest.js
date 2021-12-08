import axios from "axios";

const HOSTEL_API_BASE_URL = "http://localhost:8080/hostel";

class HostelRequest {
  // Get a complete list of hostels from the server
  getHostelList() {
    return axios.get(HOSTEL_API_BASE_URL);
  }

  // Get individual hostel data from server
  getIndividualHostel(hostelId) {
    return axios.get(HOSTEL_API_BASE_URL + "/" + hostelId);
  }

  // Post individual hostel comment to server
  postHostelComment(id, comment, rating) {
    // below is temp implementation to see post content
    if (id.hostelId == undefined) {
      // check id is hostel id
      throw new Error(
        "Invalid id argument. Id must be a hostelId.\nId received: ",
        id
      );
    }
    var req = {
      hostelId: id.hostelId,
      comment: comment,
      rating: rating,
    };
    return req;
    // todo: post hostel comment with hostelId to backend
  }
   findHostels(keyword) {
        return axios.post(HOSTEL_API_BASE_URL, {
            keyword: keyword
        });
   }
}

export default new HostelRequest();
