<template>
  <v-card flat class="mx-16 mb-12">
  <div class="comment-list">
    <div class="comment-title">Comments</div>
    <!-- <div class="sorting-buttons"> -->
      <v-layout>
        <!-- <v-spacer></v-spacer> -->
        <v-row> 
          <v-col cols="12" sm="2">
            <v-btn icon color="primary" @click="sortCommentsFromLowestToHighestRating(true)">
              <v-icon>mdi-sort-ascending</v-icon>
            </v-btn>
          </v-col>

          <v-col cols="12" sm="2">
            <v-btn icon color="primary" @click="sortCommentsFromLowestToHighestRating(false)">
              <v-icon>mdi-sort-descending</v-icon>
            </v-btn>
          </v-col>
        </v-row>
      </v-layout>
    <!-- </div> -->

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
                class="my-4"
                :length="totalPages"
                prev-icon="mdi-menu-left"
                next-icon="mdi-menu-right"
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
import HostelRequest from "../httpRequests/HostelRequest";
import Comment from "./Comment.vue";

export default {
  data() {
    return {
      commentList: [],
      pageSize: 2,
      currentPage: 1,
      totalPages: 0
    };
  },
  components: {
    Comment,
  },
  methods: {
    async getCommentList(pageNum, pageSize) {
      HostelRequest.getCommentList(this.$route.params.hostelId, pageNum, pageSize)
        .then((response) => {
          this.commentList = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    async updatePage(pageNumber) {
      this.currentPage = pageNumber;
      await this.getCommentList(pageNumber - 1, this.pageSize);
    },

    async sortCommentsFromLowestToHighestRating(isLowToHigh) {
      HostelRequest.sortCommentsByRating(
        this.$route.params.hostelId, isLowToHigh, this.currentPage - 1, this.pageSize
      ).then((response) => {
        console.log(response.data);
          this.commentList = response.data.content;
          this.totalPages = response.data.totalPages;
        })
        .catch((error) => {
          console.log(error.response.data);
        });
    },
  },
  async mounted() {
    await this.getCommentList(this.currentPage - 1, this.pageSize);
  },
};
</script>

<style scoped>
.comment-list {
  padding: 40px 40px;
  margin: 20px 0px;
  /* background: rgba(214, 238, 245, 0.5); */
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

.sorting-buttons {
  /* display: flex; */
  /* justify-content: flex-end; */
}

/* .sorting-buttons > button {
  background: transparent;
  border-radius: 10px;
  padding: 5px;
  margin: 10px;
} */

.comments {
  grid-column-start: 1;
  grid-column-end: 3;
}
</style>
