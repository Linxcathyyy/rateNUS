import axios from "axios";

const STUDY_AREA_API_BASE_URL = "http://localhost:8080/studyarea";
const ADD_COMMENT_API_BASE_URL = "http://localhost:8080/comment";
const GET_COMMENT_API_BASE_URL = "http://localhost:8080/comment/studyArea";

class StudyAreaRequest {
    // Get a partial list of studyAreas based on startIndex and endIndex
    async getStudyAreaList(pageNum, pageSize) {
        return await axios.post(STUDY_AREA_API_BASE_URL, {
            orderBy: "id",
            isLowToHigh: true,
            pageNum: pageNum,
            pageSize: pageSize,
        });
    }

    getSortedStudyAreaList(isHighToLow) {
        return axios.get(STUDY_AREA_API_BASE_URL + "/isHighToLow/" + isHighToLow);
    }

    // Get individual studyArea data from server
    getIndividualStudyArea(studyAreaId) {
        return axios.get(STUDY_AREA_API_BASE_URL + "/" + studyAreaId);
    }

    // Find studyAreas based on keywords
    findStudyAreas(keyword, pageNum, pageSize) {
        return axios.post(STUDY_AREA_API_BASE_URL + "/search", {
            keyword: keyword,
            pageNum: pageNum,
            pageSize: pageSize,
        });
    }

    // Post individual studyArea comment to server
    postStudyAreaComment(studyAreaId, comment, rating, jwtToken, userId) {
        // below is temp implementation to see post content
        if (studyAreaId == undefined) {
            // check id is studyArea id
            throw new Error(
                "Invalid id argument. Id must be a studyAreaId.\nId received: ",
                studyAreaId
            );
        }
        const headers = {
            Authorization: "Bearer " + jwtToken,
        };

        const req = {
            targetId: studyAreaId,
            rating: rating,
            text: comment,
            type: "studyArea",
            userId: userId
        };
        return axios.post(ADD_COMMENT_API_BASE_URL, req, {
            headers: headers,
        });
    }

    updateStudyArea(studyAreaId, jwtToken, username, data) {
        return axios.put(STUDY_AREA_API_BASE_URL + "/update/" + studyAreaId, data, {
          params: {
            token: jwtToken,
            username: username
          },
        });
    }
    
    deleteStudyArea(studyAreaId, jwtToken, username) {
        return axios.delete(STUDY_AREA_API_BASE_URL + "/delete/" + studyAreaId, {
          params: {
            token: jwtToken,
            username: username
          },
        });
    }

    addStudyArea(jwtToken, username, data) {
        return axios.post(STUDY_AREA_API_BASE_URL + "/new", data, {
          params: {
            token: jwtToken,
            username: username
          },
        });
      }

    // Get a partial list of comments base on startIndex and endIndex
    async getCommentList(studyAreaId, pageNum, pageSize) {
        return await axios.post(GET_COMMENT_API_BASE_URL + "/" + studyAreaId, {
            orderBy: "timestamp",
            isLowToHigh: false,
            pageNum: pageNum,
            pageSize: pageSize,
        });
    }

    async sortCommentsByRating(studyAreaId, isLowToHigh, pageNum, pageSize) {
        return await axios.post(GET_COMMENT_API_BASE_URL + "/" + studyAreaId, {
            orderBy: "rating",
            isLowToHigh: isLowToHigh,
            pageNum: pageNum,
            pageSize: pageSize,
        });
    }
}

export default new StudyAreaRequest();
