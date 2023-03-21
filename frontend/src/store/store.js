import { defineStore } from 'pinia'

export const useLoggedInStore = defineStore('alerts', {

    state: () => ({
        isLoggedIn: false,
        token: "",
        username: "",
        fullName:"",
        email:"",
        dateOfBirth:"",
        phoneNumber:"",
        address:"",
        zipCode:"",
    }),

    getters: {
    },

    actions: {
        setLoggedIn() {
            this.isLoggedIn = true;
        },
        setToken(token){
            this.token = token;
        },
        setUsername(username){
            this.username = username;
        },
        setFullName(fullName){
            this.fullName = fullName;
        },
        setEmail(email){
            this.email = email;
        },
        setDateOfBirth(dateOfBirth){
            this.dateOfBirth = dateOfBirth;
        },
        setPhoneNumber(phoneNumber){
            this.phoneNumber =phoneNumber ;
        },
        setAddress(address){
            this.address = address;
        },
        setZipCode(zipCode){
            this.zipCode = zipCode;
        },
        setUser(fullName, userName, email, dateOfBirth, phoneNumber, address, zipCode){
            this.fullName = fullName;
            this.username = userName;
            this.email = email;
            this.dateOfBirth = dateOfBirth;
            this.phoneNumber = phoneNumber;
            this.address = address;
            this.zipCode = zipCode;
        }

    },
})