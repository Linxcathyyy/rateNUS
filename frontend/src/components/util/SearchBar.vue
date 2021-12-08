<template>
    <div>
        <input id="search-bar" type="text" v-model="keyword" name="search-bar" :placeholder=searchHint />
        <button id="search-button" @click="handleSearch()">Search</button>
    </div>
    
</template>

<script>
export default {
    name: 'SearchBar',
    props: {
        searchHint: String,
    },
    data() {
       return {
            keyword: ""
       }
    },
    methods: {
        handleSearch() {
            this.$emit("handle-search", this.keyword);
        }
    }
}
</script>
