<template>
  <div class="search">
    <input
      id="search-bar"
      type="text"
      v-model="keyword"
      name="search-bar"
      :placeholder="searchHint"
      v-on:keypress.enter="handleSearch"
    />
    <button id="search-button" @click="handleSearch()">Search</button>
  </div>
</template>

<script>
export default {
  name: "SearchBar",
  props: {
    searchHint: String,
  },
  data() {
    return {
      keyword: "",
    };
  },
  methods: {
    handleSearch() {
      if (this.keyword) this.$emit("handle-search", this.keyword);
    },
  },
};
</script>

<style scoped>
.search {
  display: flex;
}

#search-bar {
  height: 2rem;
  margin: 1rem;
  border: 0;
  border-bottom: 1px solid #999;
  outline: 0;
  font-size: 1rem;
  flex-grow: 1;
}

#search-bar:focus {
  border-bottom: 1.5px solid #999;
}

#search-button {
  height: 3rem;
  width: 6rem;
  margin: auto;
  padding: auto;
  border: 0;
  border-radius: 30px;
  outline: 0;
  background: #428dff;
  text-align: center;
  color: white;
  font-size: 1rem;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
}

#search-button:hover {
  background: #569aff;
}

#search-button:active {
  background: #2c80ff;
}
</style>
