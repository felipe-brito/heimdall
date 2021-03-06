package br.com.conductor.heimdall.core.entity;

/*-
 * =========================LICENSE_START==================================
 * heimdall-core
 * ========================================================================
 * Copyright (C) 2018 Conductor Tecnologia SA
 * ========================================================================
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * ==========================LICENSE_END===================================
 */

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * This class represents a OAuthAuthorize registered to the system.
 *
 * @author <a href="https://dijalmasilva.github.io" target="_blank">Dijalma Silva</a>
 */
@Data
@Table(name = "OAUTH_AUTHORIZES")
@Entity
@DynamicUpdate
@DynamicInsert
@EqualsAndHashCode(of = {"clientId"})
@AllArgsConstructor
public class OAuthAuthorize implements Serializable {

    private static final long serialVersionUID = -591348072727902230L;

    public OAuthAuthorize() {
    }

    public OAuthAuthorize(String clientId) {
        this.clientId = clientId;
        this.generateTokenAuthorize();
    }

    @Id
    @Column(name = "CLIENT_ID", length = 250, nullable = false, unique = true)
    private String clientId;

    @Column(name = "TOKEN_AUTHORIZE", length = 250, nullable = false)
    private String tokenAuthorize;

    /**
     * This method generates a new code authorize.
     */
    public void generateTokenAuthorize() {
        String toEncode = LocalDateTime.now().toString() + "::" + clientId;
        this.tokenAuthorize = Base64.getEncoder().encodeToString(toEncode.getBytes());
    }


}
