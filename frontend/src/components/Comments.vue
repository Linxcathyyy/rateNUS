<template>
  <div class="comment-list">
    <button @click="resetCommentsSorting(comment, rating)">Reset</button>
    <button @click="sortCommentsFromLowestToHighestRating(comment, rating)">Low -> High</button>
    <button @click="sortCommentsFromHighestToLowestRating(comment, rating)">High -> Low</button>
    <div v-for="comment in commentList" :key="comment.id">
      <Comment :comment="comment" />
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Comment from "./Comment.vue";

export default {
  data() {
    return {
      commentList: [],
    };
  },
  components: {
    Comment,
  },
  methods: {
    getCommentList() {
      HostelRequest.getCommentList(this.$route.params.hostelId)
        .then((response) => {
          this.commentList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sortCommentsFromLowestToHighestRating() {
      HostelRequest.sortCommentsFromLowestToHighestRating(this.$route.params.hostelId)
        .then((response) => {
          this.commentList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sortCommentsFromHighestToLowestRating() {
      HostelRequest.sortCommentsFromHighestToLowestRating(this.$route.params.hostelId)
        .then((response) => {
          this.commentList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    resetCommentsSorting() {
      HostelRequest.resetCommentsSorting(this.$route.params.hostelId)
        .then((response) => {
          this.commentList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
  },
  mounted() {
    this.getCommentList();
  },
};
</script>

<style scoped>
.comment-list {
  padding: 20px 30px;
  margin: 20px 0px;
  background: rgba(214, 238, 245, 0.5);
  border-radius: 30px;
}
</style>
