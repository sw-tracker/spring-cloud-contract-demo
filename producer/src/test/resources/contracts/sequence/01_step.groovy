package contracts.in_sequence;

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    description "should return one"
    request{
        method GET()
        url("/sequence") {
            queryParameters {
                parameter("order", "1")
            }
        }
    }
    response {
        body("1")
        status 200
    }
}
