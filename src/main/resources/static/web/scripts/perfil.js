const app = Vue.createApp({
    data(){
        return{
            datosCliente: [],
            datosCliente2: [],
            data:{
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                password2: ""
            },
            mail:{
                nombre: "",
                email: ""
            },
            type1: "password", hidden1: true, show1: false,
            type2: "password", hidden2: true, show2: false
        }
    },
    created(){
        this.datosClienteActual()
        this.dataCliente()
    },
    methods:{
        datosClienteActual(){
            axios.get("/api/cliente/actual")
            .then(response => {
                this.datosCliente = response.data
            })
        },
        subscripcion(){
            swal({
                title: "¡Atención!",
                text: "¿Estás seguro/a que quieres subscribirte a nuestro newsletter?",
                icon: "warning",
                buttons: [true, "Continuar"]
            })
            .then(confrimation => {
                axios.post("/api/mail",`destino=${this.mail.email}&nombreUsuario=${this.mail.nombre}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                .then(response => {
                    swal({
                        title: "¡Genial!",
                        text: "Gracias por subscribirte a nuestro newsletter.",
                        icon: "success"
                    })
                    .then(confirmation => {
                        window.location.reload()
                    })
                })
            })
        },
        signOut(){
            swal({
                text: "¿Estás seguro/a que quieres cerrar su sesión?",
                icon:"warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.post("/api/logout")
                    .then(response=>{
                        window.location.replace("../index.html")
                    })
                }
            })
        },
        dataCliente(){
            const urlParams = new URLSearchParams(window.location.search);
            this.clienteID = urlParams.get("id");

            axios.get(`/api/cliente/actual/${this.clienteID}`)
            .then(response => {
                this.data.firstName = response.data.firstName
                this.data.lastName = response.data.lastName
                this.data.email = response.data.email
            })
        },
        cambioDatos(){
            swal({
                title: "Confirmación",
                text: "¿Está seguro/a que quiere guardar los datos?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(confirmation){
                    axios.put(`/api/cliente/actual/personal`,`firstName=${this.data.firstName}&lastName=${this.data.lastName}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            text: "Datos guardados.",
                            icon: "success",
                            button: "volver"
                        })
                        .then(response =>{
                            window.location.replace("home.html")
                        })
                    })
                    .catch(error => {
                        swal({
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }      
            })
        },
        cambioContraseña(){
            swal({
                title: "Confirmación",
                text: "¿Está seguro/a que desea cambiar su contraseña?",
                icon: "warning",
                buttons: true
            })
            .then(confirmation => {
                if(this.data.password === this.data.password2){
                    axios.put(`/api/cliente/actual/password`,`password=${this.data.password}`,{headers:{'content-type':'application/x-www-form-urlencoded'}})
                    .then(response => {
                        swal({
                            text: "Datos guardados.",
                            icon: "success",
                            button: "volver"
                        })
                        .then(response =>{
                            window.location.replace("home.html")
                        })
                    })
                    .catch(error => {
                        swal({
                            text: error.response.data,
                            icon: "error"
                        })
                    })
                }else{
                    swal({
                        text: "Las contraseñas no coinciden. Por favor, intente nuevamente.",
                        icon: "error"
                    })
                }      
            })
        },
        showPassword1(){
            if(this.type1 === "password"){
                this.type1 = "text"
                this.hidden1 = false
                this.show1 = true
            }else{
                this.type1 = "password"
                this.hidden1 = true
                this.show1 = false
            }
        },
        showPassword2(){
            if(this.type2 === "password"){
                this.type2 = "text"
                this.hidden2 = false
                this.show2 = true
            }else{
                this.type2 = "password"
                this.hidden2 = true
                this.show2 = false
            }
        }
    }
})
app.mount("#app")