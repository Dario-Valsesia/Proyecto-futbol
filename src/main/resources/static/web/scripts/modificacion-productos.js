const app = Vue.createApp({
    data() {
        return {
            modificarOAgregarProducto: "",
            nombreProducto: "",
            marca: "",
            costo: 0,
            porcentajeGanancia: 0,
            stock: 0,
            tallesProducto: [],
            equipoProducto: "",
            tipoProducto: "",
            imagenProducto: "",
            descripcionProducto: ""
        }
    },
    created() {
        axios.get("/api/productos")
            .then(response => {
                this.tablaProductos = response.data
            })
    },
    methods: {
        guardarProducto() {
            axios.post("/api/productos",{ "name": this.nombreProducto,
                                            "nombreProducto": this.descripcionProducto,
                                            "precioCosto": this.costo, 
                                            "porcentajeGanancia": this.porcentajeGanancia,
                                            "stock": this.stock,
                                            "marca": this.marca,
                                            "talle": this.tallesProducto, 
                                            "url": this.imagenProducto, 
                                            "equipo": this.equipoProducto, 
                                            "tipo": this.tipoProducto})
            .then(response => {
                swal({
                    text: "Producto agregado a la tienda",
                    icon: "success",
                    button: false,
                })
                setTimeout(function(){
                    location.reload()
                }, 2500)
            })
            .catch(error => {
                console.log(error.response.data)
            })
        }
    },
    computed: {
        calcularPrecio() {
            let precioFinal = 0
            if (this.costo != 0 && this.porcentajeGanancia != 0) {
                let valorAgregado = (this.costo / 100) * this.porcentajeGanancia
                precioFinal = this.costo + valorAgregado
            }
            return precioFinal
        },
        devolverTalles() {
            let tallesElegidos = []
            if (this.nombreProducto === "Botin" || this.nombreProducto === "Medias") {
                tallesElegidos = ["35", "36", "37", "38", "39", "40", "41", "42", "43", "44"]
            } else if (this.nombreProducto === "Camiseta" || this.nombreProducto === "Short") {
                tallesElegidos = ["XS", "S", "M", "L", "XL", "XXL"]
            } else if (this.nombreProducto === "Pelota") {
                tallesElegidos = ["2", "3", "4", "5"]
            }
            return tallesElegidos
        }
    }
})
let mount = app.mount("#app")