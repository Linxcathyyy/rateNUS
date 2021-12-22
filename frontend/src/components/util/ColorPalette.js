class ColorPalette {
    
    getRandomColor() {
        const colors = [
            "#FF6D00",
            "#FFA726",
            "#FFAB00",
            "#AEEA00",
            "#CDDC39",
            "#64DD17",
            "#B2FF59",
            "#00C853",
            "#00E676",
            "#00BFA5",
            "#1DE9B6",
            "#00B8D4",
            "#00E5FF",
            "#00B0FF",
            "#AA00FF",
            "#D500F9",
            "#F50057",
            "#EC407A",
          ];
        var color_idx = Math.floor(Math.random() * colors.length)
        var color = colors[color_idx];
        return color;
    }
}

export default new ColorPalette();