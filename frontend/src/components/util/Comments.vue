<template>
  <v-card flat class="my-12">
    <div class="comment-list">
      <div class="comment-title">Comments</div>
      <v-layout justify-end>
        <v-btn
          icon
          color="orange accent-4"
          class="mx-1"
          @click="sortCommentsFromLowestToHighestRating(true)"
          title="ratings low to high"
        >
          <v-icon>mdi-sort-ascending</v-icon>
        </v-btn>
        <v-btn
          icon
          color="orange accent-4"
          class="mx-1"
          @click="sortCommentsFromLowestToHighestRating(false)"
          title="ratings high to low"
        >
          <v-icon>mdi-sort-descending</v-icon>
        </v-btn>
      </v-layout>

      <div class="comments">
        <div v-for="comment in commentList" :key="comment.id">
          <Comment :comment="comment" />
        </div>
      </div>
      <div class="comments text-center">
        <v-container>
          <v-row justify="center">
            <v-col cols="8">
              <v-container class="max-width">
                <v-pagination
                  @input="updatePage"
                  v-model="currentPage"
                  :length="totalPages"
                  class="my-4"
                  prev-icon="mdi-menu-left"
                  next-icon="mdi-menu-right"
                  color="orange accent-4"
                ></v-pagination>
              </v-container>
            </v-col>
          </v-row>
        </v-container>
      </div>
    </div>
  </v-card>
</template>

<script>
import HostelRequest from "../../httpRequests/HostelRequest";
import StallRequest from "../../httpRequests/StallRequest";
import StudyAreaRequest from "../../httpRequests/StudyAreaRequest";
import Comment from "./Comment.vue";

export default {
  props: {
    type: String,
  },
  data() {
    return {
      commentList: [],
      pageSize: 1,
      currentPage: 1,
      totalPages: 0,
      isLowestToHighestRating: null,
    };
  },
  components: {
    Comment,
  },
  methods: {
    async getCommentList(pageNum, pageSize) {
      if (this.type === "hostel") {
        HostelRequest.getCommentList(
          this.$route.params.id,
          pageNum,
          pageSize
        )
          .then((response) => {
            this.commentList = response.data.content;
            this.totalPages = response.data.totalPages;
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (this.type === "stall") {
        StallRequest.getCommentList(
          this.$route.params.id,
          pageNum,
          pageSize
        )
          .then((response) => {
            this.commentList = response.data.content;
            this.totalPages = response.data.totalPages;
          })
          .catch((error) => {
            console.log(error);
          });
      } else if (this.type === "studyArea") {
         StudyAreaRequest.getCommentList(
          this.$route.params.id,
          pageNum,
          pageSize
        )
          .then((response) => {
            this.commentList = response.data.content;
            this.totalPages = response.data.totalPages;
          })
          .catch((error) => {
            console.log(error);
          });
      }
    },
    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      if (this.isLowestToHighestRating !== null) {
        await this.sortCommentsFromLowestToHighestRating(this.isLowestToHighestRating);
      } else {
        await this.getCommentList(pageNumber - 1, this.pageSize);
      }
    },

    async sortCommentsFromLowestToHighestRating(isLowToHigh) {
      console.log(this.currentPage);
      console.log("pageNum: " + this.currentPage);
      this.isLowestToHighestRating = isLowToHigh;
      if (this.type === "hostel") {
        HostelRequest.sortCommentsByRating(
          this.$route.params.id,
          isLowToHigh,
          this.currentPage - 1,
          this.pageSize
        )
          .then((response) => {
            console.log(response.data);
            this.commentList = response.data.content;
            this.totalPages = response.data.totalPages;
          })
          .catch((error) => {
            console.log(error.response.data);
          });
      } else if (this.type === "stall") {
        StallRequest.sortCommentsByRating(
          this.$route.params.id,
          isLowToHigh,
          this.currentPage - 1,
          this.pageSize
        )
          .then((response) => {
            console.log(response.data);
            this.commentList = response.data.content;
            this.totalPages = response.data.totalPages;
          })
          .catch((error) => {
            console.log(error.response.data);
          });
      } else {
        // study area
      }
    },
  },
  async mounted() {
    await this.getCommentList(this.currentPage - 1, this.pageSize);
  },
};
</script>

<style scoped>
.comment-list {
  margin: 0 1rem;
  border-radius: 30px;
  display: grid;
  grid-template-columns: 1fr 1fr;
}

.comment-title {
  text-align: left;
  margin: 0 0 auto 0;
  font-size: 30px;
  font-weight: bold;
}

.comments {
  grid-column-start: 1;
  grid-column-end: 3;
}
</style>
