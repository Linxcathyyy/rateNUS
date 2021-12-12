<template>
  <div class="comment-list">
    <div class="comment-title">Comments</div>
    <div class="sorting-buttons">
      <button @click="resetCommentsSorting(comment, rating)">Reset</button>
      <button @click="sortCommentsFromLowestToHighestRating(comment, rating)">
        Low -> High
      </button>
      <button @click="sortCommentsFromHighestToLowestRating(comment, rating)">
        High -> Low
      </button>
    </div>

    <div class="comments">
      <div v-for="comment in commentList" :key="comment.id">
        <Comment :comment="comment" />
      </div>
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
      HostelRequest.sortCommentsFromLowestToHighestRating(
        this.$route.params.hostelId
      )
        .then((response) => {
          this.commentList = response.data;
        })
        .catch((error) => {
          console.log(error);
        });
    },
    sortCommentsFromHighestToLowestRating() {
      HostelRequest.sortCommentsFromHighestToLowestRating(
        this.$route.params.hostelId
      )
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
  padding: 40px 40px;
  margin: 20px 0px;
  background: rgba(214, 238, 245, 0.5);
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
  display: flex;
  justify-content: flex-end;
}

.sorting-buttons > button {
  background: transparent;
  border-radius: 10px;
  padding: 5px;
  margin: 10px;
}

.comments {
  grid-column-start: 1;
  grid-column-end: 3;
}
</style>
