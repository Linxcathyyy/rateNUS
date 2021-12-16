<template>
<v-card flat class="mx-16 mb-12">
  <div class="comment-form">
    <form>
      <div>
        <label class="rating-label"> Rating: </label>
        {{ rating }} / 5
        <div class="rating-display">
          <input
            class="rating"
            max="5"
            step="1"
            type="range"
            value="3"
            v-model="rating"
          />
        </div>
      </div>
      <textarea-autosize
        class="comment"
        placeholder="Join the discussion..."
        ref="myTextarea"
        type="text"
        required
        :min-height="30"
        :max-height="350"
        v-model="comment"
      />
      <div class="submit">
        <button @click="handleSubmit(comment, rating)">Submit</button>
      </div>
    </form>
  </div>
</v-card>
</template>

<script>
import HostelRequest from "../httpRequests/HostelRequest";
import Vue from "vue";
import TextareaAutosize from "vue-textarea-autosize";
Vue.use(TextareaAutosize);
export default {
  data() {
    return {
      comment: "",
      rating: 5,
      rowsNum: 1,
      isExpanded: false,
    };
  },
  methods: {
    handleSubmit(comment, rating) {
      var id = this.$route.params.hostelId;

      var jwtToken = this.$store.getters.jwtToken;
      console.log("jwtToken", jwtToken);
      HostelRequest.postHostelComment(id, comment, rating, jwtToken)
        .then(() => {
          window.confirm("Successfully added a comment!");
          // reload current page
          location.reload();
        })
        .catch((err) => {
          console.log(err);
          window.confirm("Failed adding a comment");
        });
      // reset comment inputs
      this.comment = "";
      this.rating = 5;
    },
  },
};
</script>

<style scoped>
.comment-form {
  max-width: 100%;
  /* background: rgba(214, 238, 245, 0.5); */
  text-align: left;
  border-radius: 30px;
  padding: 30px 40px;
}

label {
  display: inline-block;
  margin: 10px 0px;
  font-weight: bold;
}

.comment {
  width: 100%;
  max-width: 100%;
  background: transparent;
  display: block;
  font-size: 120%;
  padding: 15px 0px 0px 0px;
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
