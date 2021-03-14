import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return two"
    request{
        method GET()
        url("/sequence") {
            queryParameters {
                parameter("order", "2")
            }
        }
    }
    response {
        body("2")
        status 200
    }
}
