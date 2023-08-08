import { apiUrl } from '../../constants/api'

export default defineEventHandler(async (event) => {
    console.log(event.req.headers.authorization)
    await $fetch('/user/info', {
        headers: {
            Authorization: event.req.headers.authorization
        },
        baseURL: apiUrl
    }).then((res: any) => {
        console.log(res)
            return res
        }).catch((err: any) => {
            console.log(err)
            return err
        })
})
