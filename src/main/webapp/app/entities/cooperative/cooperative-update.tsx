import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IUtilisateur } from 'app/shared/model/utilisateur.model';
import { getEntities as getUtilisateurs } from 'app/entities/utilisateur/utilisateur.reducer';
import { IBoutique } from 'app/shared/model/boutique.model';
import { getEntities as getBoutiques } from 'app/entities/boutique/boutique.reducer';
import { ICooperative } from 'app/shared/model/cooperative.model';
import { getEntity, updateEntity, createEntity, reset } from './cooperative.reducer';

export const CooperativeUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const utilisateurs = useAppSelector(state => state.utilisateur.entities);
  const boutiques = useAppSelector(state => state.boutique.entities);
  const cooperativeEntity = useAppSelector(state => state.cooperative.entity);
  const loading = useAppSelector(state => state.cooperative.loading);
  const updating = useAppSelector(state => state.cooperative.updating);
  const updateSuccess = useAppSelector(state => state.cooperative.updateSuccess);

  const handleClose = () => {
    navigate('/cooperative');
  };

  useEffect(() => {
    if (!isNew) {
      dispatch(getEntity(id));
    }

    dispatch(getUtilisateurs({}));
    dispatch(getBoutiques({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    const entity = {
      ...cooperativeEntity,
      ...values,
      boutiques: mapIdList(values.boutiques),
      idDG: utilisateurs.find(it => it.id.toString() === values.idDG.toString()),
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
          ...cooperativeEntity,
          idDG: cooperativeEntity?.idDG?.id,
          boutiques: cooperativeEntity?.boutiques?.map(e => e.id.toString()),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="coopcycleApp.cooperative.home.createOrEditLabel" data-cy="CooperativeCreateUpdateHeading">
            <Translate contentKey="coopcycleApp.cooperative.home.createOrEditLabel">Create or edit a Cooperative</Translate>
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
                  id="cooperative-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('coopcycleApp.cooperative.name')}
                id="cooperative-name"
                name="name"
                data-cy="name"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('coopcycleApp.cooperative.zone')}
                id="cooperative-zone"
                name="zone"
                data-cy="zone"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                id="cooperative-idDG"
                name="idDG"
                data-cy="idDG"
                label={translate('coopcycleApp.cooperative.idDG')}
                type="select"
                required
              >
                <option value="" key="0" />
                {utilisateurs
                  ? utilisateurs.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <FormText>
                <Translate contentKey="entity.validation.required">This field is required.</Translate>
              </FormText>
              <ValidatedField
                label={translate('coopcycleApp.cooperative.boutique')}
                id="cooperative-boutique"
                data-cy="boutique"
                type="select"
                multiple
                name="boutiques"
              >
                <option value="" key="0" />
                {boutiques
                  ? boutiques.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/cooperative" replace color="info">
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

export default CooperativeUpdate;
