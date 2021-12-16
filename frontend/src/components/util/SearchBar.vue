<template>
  <div>
      <v-text-field
        solo
        v-model="keyword"
        name="search-bar"
        :placeholder="searchHint"
        color="grey darken-3"
        :append-icon="searchIcon"
        v-on:keypress.enter="handleSearch"
        @click:append="handleSearch()"
      >
      </v-text-field>
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
      searchIcon: "mdi-magnify"
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
  color: white;
  box-shadow: rgba(0, 0, 0, 0.15) 1.95px 1.95px 2.6px;
}

#search-button:hover {
  background: #569aff;
}

#search-button:active {
  background: #2c80ff;
}
</style>
