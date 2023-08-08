import { apiPrefix, apiUrl} from '~/constants/api'

export function getBaseUrl() {
    if (process.server) {
        return apiUrl
    } else {
        return apiPrefix
    }
}

