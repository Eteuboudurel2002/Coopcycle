import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText, UncontrolledTooltip } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICooperative } from 'app/shared/model/cooperative.model';
import { getEntities as getCooperatives } from 'app/entities/cooperative/cooperative.reducer';
import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { Role } from 'app/shared/model/enumerations/role.model';
import { getEntity, updateEntity, createEntity, reset } from './utilisateur.reducer';

export const UtilisateurUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const cooperatives = useAppSelector(state => state.cooperative.entities);
  const utilisateurEntity = useAppSelector(state => state.utilisateur.entity);
  const loading = useAppSelector(state => state.utilisateur.loading);
  const updating = useAppSelector(state => state.utilisateur.updating);
  const updateSuccess = useAppSelector(state => state.utilisateur.updateSuccess);
  const roleValues = Object.keys(Role);

  const handleClose = () => {
    navigate('/utilisateur');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getCooperatives({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...utilisateurEntity,
      ...values,
      idCoop: cooperatives.find(it => it.id.toString() === values.idCoop.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {}
      : {
          role: 'CLIENT',
          ...utilisateurEntity,
          idCoop: utilisateurEntity?.idCoop?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="coopcycleApp.utilisateur.home.createOrEditLabel" data-cy="UtilisateurCreateUpdateHeading">
            <Translate contentKey="coopcycleApp.utilisateur.home.createOrEditLabel">Create or edit a Utilisateur</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="utilisateur-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('coopcycleApp.utilisateur.name')}
                id="utilisateur-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <UncontrolledTooltip target="nameLabel">
                <Translate contentKey="coopcycleApp.utilisateur.help.name" />
              </UncontrolledTooltip>
              <ValidatedField
                label={translate('coopcycleApp.utilisateur.age')}
                id="utilisateur-age"
                name="age"
                data-cy="age"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  min: { value: 18, message: translate('entity.validation.min', { min: 18 }) },
                  max: { value: 120, message: translate('entity.validation.max', { max: 120 }) },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.utilisateur.address')}
                id="utilisateur-address"
                name="address"
                data-cy="address"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.utilisateur.email')}
                id="utilisateur-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  pattern: {
                    value: /^[\w.%+-]+@[A-Za-z0-9.-]\.[a-zA-Z\.]{2,6}$/,
                    message: translate('entity.validation.pattern', { pattern: '^[\\w.%+-]+@[A-Za-z0-9.-]\\.[a-zA-Z\\.]{2,6}$' }),
                  },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.utilisateur.tel')}
                id="utilisateur-tel"
                name="tel"
                data-cy="tel"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  pattern: {
                    value: /^0[1-9](?:[\s]?[0-9]{2}){4}$/,
                    message: translate('entity.validation.pattern', { pattern: '^0[1-9](?:[\\s]?[0-9]{2}){4}$' }),
                  },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.utilisateur.role')}
                id="utilisateur-role"
                name="role"
                data-cy="role"
                type="select"
              >
                {roleValues.map(role => (
                  <option value={role} key={role}>
                    {translate('coopcycleApp.Role.' + role)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="utilisateur-idCoop"
                name="idCoop"
                data-cy="idCoop"
                label={translate('coopcycleApp.utilisateur.idCoop')}
                type="select"
              >
                <option value="" key="0" />
                {cooperatives
                  ? cooperatives.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/utilisateur" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default UtilisateurUpdate;
