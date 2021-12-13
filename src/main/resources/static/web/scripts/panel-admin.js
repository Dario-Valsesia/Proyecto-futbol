const app = Vue.createApp({
    data() {
        return {

            tablaProductos: [],
            panelPrincipal: true,
            modificarOAgregarProducto: "",
            productoModificado: 0,
            productoEliminado: "",
            buscarProducto: "",
            // PARAMETROS DEL REQUEST
            idProducto: 0,
            nombreProducto: "",
            descripcionProducto: "",
            marca: "",
            costo: 0,
            porcentajeGanancia: 0,
            stock: 0,
            tallesProducto: [],
            equipoProducto: "",
            tipoProducto: "",
            imagenProducto: ""

        }
    },
    created() {

        axios.get("/api/productos")
        .then(response => {
            this.tablaProductos = response.data
        })
        .catch(error => {
            console.log(error.response.data)
        })

    },
    methods: {

        guardarProducto() {
            if (this.marca === "") {
                swal({
                    text: "Elija una marca para su producto",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.descripcionProducto === ""){
                swal({
                    text: "Elija un nombre para su producto",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.costo <= 0){
                swal({
                    text: "El costo del producto debe der mayor a 0",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.porcentajeGanancia <= 0){
                swal({
                    text: "Indique el porcentaje de ganancia que desea obtener",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.stock <= 0){
                swal({
                    text: "Indique de cuantas unidades del producto dispone",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.tallesProducto.length < 1){
                swal({
                    text: "Seleccione los talles disponibles del producto",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.nombreProducto === "Botin" && this.tipoProducto === ""){
                swal({
                    text: "Indique el tipo de botín",
                    timer: 3000,
                    icon: "warning"
                })
            }else if(this.imagenProducto === ""){
                swal({
                    text: "Seleccione una imagen para su producto",
                    timer: 3000,
                    icon: "warning"
                })
            } else {
                axios.post("/api/productos", {
                    "name": this.nombreProducto,
                    "nombreProducto": this.descripcionProducto,
                    "precioCosto": this.costo,
                    "porcentajeGanancia": this.porcentajeGanancia,
                    "stock": this.stock,
                    "marca": this.marca,
                    "talle": this.tallesProducto,
                    "url": this.imagenProducto,
                    "equipo": this.equipoProducto,
                    "tipo": this.tipoProducto
                })
                .then(response => {
                    swal({
                        text: "Producto registrado con éxito",
                        icon: "success",
                        button: false,
                    })
                    setTimeout(function () {
                        location.reload()
                    }, 2500)
                })
                .catch(error => {
                    swal({
                        text: "Hubo un error en el registro",
                        timer: 3000,
                        icon: "error"
                    })
                })
            }
        },

        elegirProducto(value){
            this.productoModificado = value
        },

        productoElegidoParaEliminar(value){
            this.productoEliminado = value
            this.idProducto = value
            swal({
                text: "¿Desea eliminar el producto? No se podrá recuperar luego",
                icon: "warning",
                buttons: true,
                dangerMode: true
            })
            .then(response => {
                if(response){
                    this.eliminarProducto()
                } else {
                    this.idProducto = 0
                }
            })
        },

        confirmarModificacion(){
            swal({
                text: "¿Desea efectivizar los cambios? No se podrán revertir luego de modificarlos",
                icon: "warning",
                buttons: true,
                dangerMode: true
            })
            .then(response => {
                if(response){
                    this.guardarModificacion()
                }
            })
        },

        guardarModificacion(){
            axios.put('/api/productos/modificar',`id=${this.idProducto}&nombreProducto=${this.descripcionProducto}&
            costoProducto=${this.costo}&porcentajeGanancia=${this.porcentajeGanancia}&stockProducto=${this.stock}&
            tallesProducto=${this.tallesProducto}&imagenProducto=${this.imagenProducto}`,
            {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                swal({
                    text: "Producto modificado con éxito",
                    icon: "success",
                    button: false,
                })
                setTimeout(function () {
                    location.reload()
                }, 2500)
            })
            .catch(error => {
                swal({
                    text: "Error en la modificación",
                    icon: "error",
                    button: false,
                })
            })
        },

        eliminarProducto(){
            axios.put('/api/productos/eliminar',`id=${this.idProducto}`,
            {headers:{'content-type':'application/x-www-form-urlencoded'}})
            .then(response => {
                swal({
                    text: "Producto eliminado de la tienda",
                    icon: "success",
                    button: false,
                })
                setTimeout(function () {
                    location.reload()
                }, 2500)
            })
            .catch(error => {
                swal({
                    text: "Error al eliminar producto",
                    icon: "error",
                    button: false,
                })
            })
        },

        volverAOpcionAnterior(){
            if(this.buscarProductoModificar.length > 0){
                this.productoModificado = ""
                this.idProducto = 0
                this.descripcionProducto = ""
                this.costo = 0
                this.tallesProducto = []
                this.imagenProducto = ""
                this.buscarProducto = ""
            }
            else if(this.modificarOAgregarProducto === "modificarProducto" || this.modificarOAgregarProducto === "eliminarProducto"){
                this.modificarOAgregarProducto = ""
                this.buscarProducto = ""
            }
            else if(this.nombreProducto != "" && this.modificarOAgregarProducto === "nuevoProducto"){
                this.nombreProducto = ""
                this.buscarProducto = ""
            }
            else if(this.modificarOAgregarProducto === "nuevoProducto" && this.nombreProducto === ""){
                this.modificarOAgregarProducto = ""
                this.buscarProducto = ""

            }else if(this.panelPrincipal === true){
                this.panelPrincipal = false
                this.buscarProducto = ""

            }else {
                this.panelPrincipal = true
                this.buscarProducto = ""
                
            }
        },
        desloguearse(){
            axios.post("/api/logout")
            .then(response=>{
                window.location.replace("../manager.html")
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
                tallesElegidos = ["32","33","34","35", "36", "37", "38", "39", "40", "41", "42", "43", "44"]
            } else if (this.nombreProducto === "Camiseta" || this.nombreProducto === "Short") {
                tallesElegidos = ["XS", "S", "M", "L", "XL", "XXL"]
            } else if (this.nombreProducto === "Pelota") {
                tallesElegidos = ["2", "3", "4", "5"]
            }
            return tallesElegidos
        },

        buscarProductoModificar(){
            let tablaProductoModificar = []
            if(this.productoModificado != ""){
                tablaProductoModificar = this.tablaProductos.filter(producto => producto.id === this.productoModificado)
                this.idProducto = tablaProductoModificar[0].id
                this.descripcionProducto = tablaProductoModificar[0].nombreProducto
                this.costo = tablaProductoModificar[0].costo
                this.tallesProducto = tablaProductoModificar[0].talle
                this.imagenProducto = tablaProductoModificar[0].urlImg
            }
            return tablaProductoModificar
        },

        confirmarEliminacion(){
            if(this.productoEliminado != ""){
                swal({
                    text: "¿Desea eliminar el producto? No se podrá recuperar luego",
                    icon: "warning",
                    buttons: true,
                    dangerMode: true
                })
                .then(response => {
                    if(response){
                        this.eliminarProducto()
                    } else {
                        this.idProducto = 0
                    }
                })
            }
        },

        filtroDeBusqueda(){
            if(this.buscarProducto === ""){
                return this.tablaProductos
            }
            if(this.buscarProducto != ''){
                let tablaProductosFiltrada = this.tablaProductos.filter(producto => producto.nombreProducto.toLowerCase().includes(this.buscarProducto.toLowerCase()))
                
                if(tablaProductosFiltrada.length === 0){
                    return false
                }
                else if(tablaProductosFiltrada.length > 0){
                    return tablaProductosFiltrada
                }
            }
        }
    }
})
let mount = app.mount("#app")