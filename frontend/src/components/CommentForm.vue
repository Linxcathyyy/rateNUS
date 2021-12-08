<template>
  <div class="comment-form">
    <form>
      <label>Add your comment:</label>
      <textarea
        class="comment"
        type="text"
        required
        v-model="comment"
        rows="5"
      />
      <label class="rating-label"> Rating: </label>
      <div class="rating-display">
        <input
          class="rating"
          max="5"
          step="1"
          type="range"
          value="3"
          v-model="rating"
        />
        <div>{{ rating }} / 5</div>
      </div>
    </form>
    <div class="submit">
      <button @click="handleSubmit(comment, rating)">Submit</button>
    </div>
  </div>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
export default {
  data() {
    return {
      comment: "",
      rating: 5,
    };
  },
  methods: {
    handleSubmit(comment, rating) {
      var id = this.$route.params;
      HostelRequest.postHostelComment(id, comment, rating);
      // console.log(HostelRequest.postHostelComment(id, comment, rating));
    },
  },
};
</script>

<style scoped>
.comment-form {
  max-width: 50%;
  background: #eee;
  text-align: left;
  border-radius: 30px;
  padding: 1rem 2rem;
}

label {
  color: #666;
  display: inline-block;
  margin: 20px 0px;
  text-transform: uppercase;
  letter-spacing: 1px;
  font-weight: bold;
}

.comment {
  width: 100%;
  max-width: 100%;
  background: transparent;
  display: block;
  font-size: 120%;
  box-sizing: border-box;
  border: none;
  border-bottom: 1px solid #999;
  color: #555;
  outline: none;
}

.comment :focus {
  border-color: #555;
}

.rating-display {
  display: flex;
  column-gap: 2rem;
}

.rating {
  max-width: 300px;
}

.submit {
  text-align: center;
}

button {
  background: #428dff;
  border: 0;
  padding: 10px 20px;
  margin-top: 20px;
  color: white;
  border-radius: 20px;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
}

button:hover {
  background: #569aff;
}

button:active {
  background: #2c80ff;
}
</style>
