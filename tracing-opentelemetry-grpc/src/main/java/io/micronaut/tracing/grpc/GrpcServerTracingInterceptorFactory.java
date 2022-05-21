/*
 * Copyright 2017-2022 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.tracing.grpc;

import io.grpc.ServerInterceptor;
import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import io.micronaut.context.annotation.Requires;
import io.opentelemetry.api.OpenTelemetry;
import io.opentelemetry.instrumentation.grpc.v1_6.GrpcTelemetry;
import jakarta.inject.Singleton;

import javax.annotation.Nonnull;

/**
 * Factory that builds the Tracing interceptors.
 *
 * @author Nemanja Mikic
 */
@Factory
public class GrpcServerTracingInterceptorFactory {

    /**
     * The server interceptor.
     *
     * @param openTelemetry The openTelemetry
     * @return The server interceptor
     */
    @Nonnull
    @Singleton
    @Bean
    @Requires(beans = OpenTelemetry.class)
    protected ServerInterceptor serverTracingInterceptor(OpenTelemetry openTelemetry) {
        return GrpcTelemetry.create(openTelemetry).newServerInterceptor();
    }

}